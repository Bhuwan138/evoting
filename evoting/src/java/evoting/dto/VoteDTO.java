/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dto;

/**
 *
 * @author Bhuwan Pandey
 */
public class VoteDTO {
    private String candidateId;
    private String voterId;

    public VoteDTO() {
    }

    public VoteDTO(String candidateId, String voterId) {
        this.candidateId = candidateId;
        this.voterId = voterId;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VoteDTO{");
        sb.append("candidateId=").append(candidateId);
        sb.append(", voterId=").append(voterId);
        sb.append('}');
        return sb.toString();
    }
    
    
}
