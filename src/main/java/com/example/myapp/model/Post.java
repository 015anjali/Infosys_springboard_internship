package com.example.myapp.model;

import jakarta.persistence.*;
import java.time.Instant;
@Entity
public class Post {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
      //  @NotBlank
        @Column
        private String title;
        @Lob
        @Column
       // @NotEmpty
        private String content;
        @Column
        private Instant createdOn;
    
        public Long getId() {
            return id;
        }
    
        public void setId(Long id) {
            this.id = id;
        }
    
        public String getTitle() {
            return title;
        }
    
        public void setTitle(String title) {
            this.title = title;
        }
    
        public String getContent() {
            return content;
        }
    
        public void setContent(String content) {
            this.content = content;
        }
    
        public Instant getCreatedOn() {
            return createdOn;
        }
    
        public void setCreatedOn(Instant now) {
            this.createdOn = createdOn;
        }
    
}
