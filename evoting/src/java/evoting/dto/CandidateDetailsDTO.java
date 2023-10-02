
package evoting.dto;

import java.util.Objects;

public class CandidateDetailsDTO {
   private String candidateId;
   private String userId;
   private String candidateName;
   private String symbol;
   private String party;
   private String city;

    public CandidateDetailsDTO() {
    }

    public CandidateDetailsDTO(String candidateId, String userId, String candidateName, String symbol, String party, String sity) {
        this.candidateId = candidateId;
        this.userId = userId;
        this.candidateName = candidateName;
        this.symbol = symbol;
        this.party = party;
        this.city = sity;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CandidateDetailsDTO{" + "candidateId=" + candidateId + ", userId=" + userId + ", candidateName=" + candidateName + ", symbol=" + symbol + ", party=" + party + ", sity=" + city + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.candidateId);
        hash = 97 * hash + Objects.hashCode(this.userId);
        hash = 97 * hash + Objects.hashCode(this.candidateName);
        hash = 97 * hash + Objects.hashCode(this.symbol);
        hash = 97 * hash + Objects.hashCode(this.party);
        hash = 97 * hash + Objects.hashCode(this.city);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CandidateDetailsDTO other = (CandidateDetailsDTO) obj;
        if (!Objects.equals(this.candidateId, other.candidateId)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        if (!Objects.equals(this.candidateName, other.candidateName)) {
            return false;
        }
        if (!Objects.equals(this.symbol, other.symbol)) {
            return false;
        }
        if (!Objects.equals(this.party, other.party)) {
            return false;
        }
        return Objects.equals(this.city, other.city);
    }
    
    
   
   
}
