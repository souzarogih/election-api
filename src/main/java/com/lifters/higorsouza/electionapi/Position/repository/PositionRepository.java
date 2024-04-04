package com.lifters.higorsouza.electionapi.Position.repository;

import com.lifters.higorsouza.electionapi.Position.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PositionRepository extends JpaRepository<Position, UUID> {
}
