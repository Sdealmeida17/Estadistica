package org.e2e.labe2e02.review.application;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.review.domain.Review;
import org.e2e.labe2e02.review.domain.ReviewService;
import org.e2e.labe2e02.review.dto.DriverReviewResponseDto;
import org.e2e.labe2e02.review.dto.ReviewRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<> createReview() {
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<> deleteReviewById() {
    }

    @GetMapping("/{driverId}")
    public ResponseEntity<Page<>> getDriverReviewsByDriverId() {
    }
}