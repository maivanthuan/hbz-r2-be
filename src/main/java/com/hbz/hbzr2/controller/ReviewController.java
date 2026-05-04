package com.hbz.hbzr2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hbz.hbzr2.model.Review;
import com.hbz.hbzr2.model.ReviewDTO;
import com.hbz.hbzr2.repository.ReviewRepository;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @GetMapping
    public Page<ReviewDTO> getAuthor(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "5") int size)
    {
        Page<Review> reviewPage = reviewRepository.findAllByOrderByIdAsc(PageRequest.of(page, size));
        return reviewPage.map(review -> {
            ReviewDTO dto = new ReviewDTO();
            dto.setId(review.getId());
            dto.setContent(review.getContent());
            if(review.getBook()!=null){
                dto.setBookName(review.getBook().getTitle());
                if (review.getBook().getAuthor()!=null) {
                    dto.setAuthorName(review.getBook().getAuthor().getName());
                    
                }
            } 
            return dto;          
        });
    }
    @PostMapping
    public Review createReview(@RequestBody Review review){
        return reviewRepository.save(review);
    }
@PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id, @RequestBody Review newReview) {
        return reviewRepository.findById(id)
                .map(review -> {
                    review.setContent(newReview.getContent());
                    return reviewRepository.save(review);
                })
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Review với id: " + id));
    }
    @GetMapping("/all")
    public List<Review> getAll(){
        return reviewRepository.findAll();
    }
    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id){
        reviewRepository.deleteById(id);
    }


}
