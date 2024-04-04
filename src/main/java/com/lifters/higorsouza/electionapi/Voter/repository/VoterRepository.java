package com.lifters.higorsouza.electionapi.Voter.repository;

import com.lifters.higorsouza.electionapi.Voter.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VoterRepository extends JpaRepository<Voter, UUID> {
}
