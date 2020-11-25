package com.mmpr.PedalivDaliBackend.repository;

import com.mmpr.PedalivDaliBackend.model.Category;
import com.mmpr.PedalivDaliBackend.model.Thumbnail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Long> {
}
