package com.embarkx.firstjobapp.review;

import com.embarkx.firstjobapp.company.Company;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review){
        boolean isReviewSaved =  reviewService.addReview(companyId, review);
        if(isReviewSaved) {
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("Review not saved", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId){
        Review review = reviewService.getReviewById(companyId, reviewId);
        if (review == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(review, HttpStatus.OK);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId,
                                               @RequestBody Review review){
        boolean isReviewUpdated = reviewService.updateReview(companyId, reviewId, review);
        if(isReviewUpdated) {
            return new ResponseEntity<>("Review updated successfully", HttpStatus.OK);
        }else{

        return new ResponseEntity<>("Review not updated", HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId,
                                               @PathVariable Long reviewId){
        boolean  isReviewDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isReviewDeleted)
            return new ResponseEntity<>("Review deleted",  HttpStatus.OK );
        else
        return new ResponseEntity<>("Review not deleted", HttpStatus.NOT_FOUND);

    }



}
