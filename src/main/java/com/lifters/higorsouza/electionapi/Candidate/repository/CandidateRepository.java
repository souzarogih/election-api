package com.lifters.higorsouza.electionapi.Candidate.repository;

import com.lifters.higorsouza.electionapi.Candidate.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, UUID> {
}
