/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dto;

/**
 *
 * @author Bhuwan Pandey
 */
public class CandidateInfoDTO {
    private String candidateId;
    private String candidateName;
    private String party;
    private String symbol;

    public CandidateInfoDTO() {
    }

    public CandidateInfoDTO(String candidateId, String candidateName, String party, String symbol) {
        this.candidateId = candidateId;
        this.candidateName = candidateName;
        this.party = party;
        this.symbol = symbol;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CandidateInfoDTO{");
        sb.append("candidateId=").append(candidateId);
        sb.append(", candidateName=").append(candidateName);
        sb.append(", party=").append(party);
        sb.append(", symbol=").append(symbol);
        sb.append('}');
        return sb.toString();
    }
    
     
}
