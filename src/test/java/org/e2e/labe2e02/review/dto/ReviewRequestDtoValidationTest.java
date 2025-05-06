package org.e2e.labe2e02.review.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewRequestDtoValidationTest {
    private Validator validator;

    @BeforeEach
    void setUp() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        validator = localValidatorFactoryBean;
    }

    @Test
    void shouldFailValidationWhenCommentIsNull() {
        ReviewRequestDto newReviewDto = new ReviewRequestDto();
        newReviewDto.setComment(null);
        newReviewDto.setRating(5);
        newReviewDto.setRideId(123L);
        newReviewDto.setTargetId(456L);
        Set<ConstraintViolation<ReviewRequestDto>> violations = validator.validate(newReviewDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenRatingIsNull() {
        ReviewRequestDto newReviewDto = new ReviewRequestDto();
        newReviewDto.setComment("Great service");
        newReviewDto.setRating(null);
        newReviewDto.setRideId(123L);
        newReviewDto.setTargetId(456L);
        Set<ConstraintViolation<ReviewRequestDto>> violations = validator.validate(newReviewDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenRatingIsBelowMinimum() {
        ReviewRequestDto newReviewDto = new ReviewRequestDto();
        newReviewDto.setComment("Great service");
        newReviewDto.setRating(-1);
        newReviewDto.setRideId(123L);
        newReviewDto.setTargetId(456L);
        Set<ConstraintViolation<ReviewRequestDto>> violations = validator.validate(newReviewDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenRatingIsAboveMaximum() {
        ReviewRequestDto newReviewDto = new ReviewRequestDto();
        newReviewDto.setComment("Great service");
        newReviewDto.setRating(6);
        newReviewDto.setRideId(123L);
        newReviewDto.setTargetId(456L);
        Set<ConstraintViolation<ReviewRequestDto>> violations = validator.validate(newReviewDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenRideIdIsNull() {
        ReviewRequestDto newReviewDto = new ReviewRequestDto();
        newReviewDto.setComment("Great service");
        newReviewDto.setRating(5);
        newReviewDto.setRideId(null);
        newReviewDto.setTargetId(456L);
        Set<ConstraintViolation<ReviewRequestDto>> violations = validator.validate(newReviewDto);
        assertEquals(1, violations.size());
    }

    @Test
    void shouldFailValidationWhenTargetIdIsNull() {
        ReviewRequestDto newReviewDto = new ReviewRequestDto();
        newReviewDto.setComment("Great service");
        newReviewDto.setRating(5);
        newReviewDto.setRideId(123L);
        newReviewDto.setTargetId(null);
        Set<ConstraintViolation<ReviewRequestDto>> violations = validator.validate(newReviewDto);
        assertEquals(1, violations.size());
    }
}
