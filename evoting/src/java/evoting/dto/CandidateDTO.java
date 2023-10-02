/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dto;

import java.io.InputStream;

/**
 *
 * @author Bhuwan Pandey
 */
public class CandidateDTO {
    private String candidateId;
    private String party;
    private String userId;
    private String city;
    private InputStream symbol;

    public CandidateDTO() {
    }

    public CandidateDTO(String candidateId, String party, String userId, String city, InputStream symbol) {
        this.candidateId = candidateId;
        this.party = party;
        this.userId = userId;
        this.city = city;
        this.symbol = symbol;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public InputStream getSymbol() {
        return symbol;
    }

    public void setSymbol(InputStream symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CandidateDTO{");
        sb.append("candidateId=").append(candidateId);
        sb.append(", party=").append(party);
        sb.append(", userId=").append(userId);
        sb.append(", city=").append(city);
        sb.append(", symbol=").append(symbol);
        sb.append('}');
        return sb.toString();
    }
    
     
    
}
