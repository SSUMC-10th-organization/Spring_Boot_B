package com.example.umc10th.domain.review.service;

import com.example.umc10th.domain.review.dto.ReviewReqDTO;
import com.example.umc10th.domain.review.dto.ReviewResDTO;
import com.example.umc10th.domain.restaurant.entity.Restaurant;
import com.example.umc10th.domain.review.entity.Review;
import com.example.umc10th.domain.review.entity.ReviewImage;
import com.example.umc10th.domain.user.entity.User;
import com.example.umc10th.domain.restaurant.repository.RestaurantRepository;
import com.example.umc10th.domain.review.repository.ReviewImageRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public ReviewResDTO.CreateReviewResponse createReview(Long userId, Long restaurantId, ReviewReqDTO.Create request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        Restaurant restaurant = restaurantRepository.findByIdAndStatus(restaurantId, Restaurant.Status.OPEN)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 식당입니다."));

        Review review = Review.builder()
                .user(user)
                .restaurant(restaurant)
                .rate(request.getRate())
                .content(request.getContent())
                .build();
        reviewRepository.save(review);

        // 이미지 저장 (실제로는 S3 업로드 후 URL 저장)
        if (request.getImages() != null && !request.getImages().isEmpty()) {
            List<ReviewImage> reviewImages = request.getImages().stream()
                    .map(MultipartFile::getOriginalFilename)   // TODO: S3 업로드 후 URL로 교체
                    .map(url -> ReviewImage.builder()
                            .review(review)
                            .imageUrl(url)
                            .build())
                    .collect(Collectors.toList());
            reviewImageRepository.saveAll(reviewImages);
        }

        return ReviewResDTO.CreateReviewResponse.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    public ReviewResDTO.GetReviewResponse getRestaurantReviews(Long restaurantId, Long cursor, int size) {

        PageRequest pageRequest = PageRequest.of(0, size);

        Slice<Review> slice = (cursor == null)
                ? reviewRepository.findByRestaurantId(restaurantId, pageRequest)
                : reviewRepository.findByRestaurantIdWithCursor(restaurantId, cursor, pageRequest);

        List<ReviewResDTO.ReviewSummary> summaries = slice.getContent().stream()
                .map(review -> {
                    List<String> imageUrls = reviewImageRepository.findAllByReviewId(review.getId())
                            .stream()
                            .map(ReviewImage::getImageUrl)
                            .collect(Collectors.toList());

                    return ReviewResDTO.ReviewSummary.builder()
                            .reviewId(review.getId())
                            .userName(review.getUser().getName())
                            .rate(review.getRate())
                            .content(review.getContent())
                            .images(imageUrls.isEmpty() ? Collections.emptyList() : imageUrls)
                            .createdAt(review.getCreatedAt())
                            .build();
                })
                .collect(Collectors.toList());

        Long nextCursor = slice.hasNext()
                ? slice.getContent().get(slice.getContent().size() - 1).getId()
                : null;

        return ReviewResDTO.GetReviewResponse.builder()
                .reviews(summaries)
                .nextCursor(nextCursor)
                .hasNext(slice.hasNext())
                .build();
    }
}