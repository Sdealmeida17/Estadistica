package org.e2e.labe2e02.review.domain;

import lombok.RequiredArgsConstructor;
import org.e2e.labe2e02.driver.exception.DriverNotFoundException;
import org.e2e.labe2e02.driver.infrastructure.DriverRepository;
import org.e2e.labe2e02.review.dto.DriverReviewResponseDto;
import org.e2e.labe2e02.review.dto.ReviewRequestDto;
import org.e2e.labe2e02.review.exception.ReviewNotFoundException;
import org.e2e.labe2e02.review.infrastructure.ReviewRepository;
import org.e2e.labe2e02.ride.domain.Ride;
import org.e2e.labe2e02.ride.exception.RideNotFoundException;
import org.e2e.labe2e02.ride.infrastructure.RideRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    private final RideRepository rideRepository;

    private final DriverRepository driverRepository;

    private final ModelMapper modelMapper;

    public Review createReview() {
    }

    public void deleteReviewById() {
    }

    public Page<> getReviewsByDriverId() {
    }
}