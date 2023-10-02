/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDTO;
import evoting.dto.CandidateDetailsDTO;
import evoting.dto.CandidateInfoDTO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

/**
 *
 * @author Bhuwan Pandey
 */
public class CandidateDAO {
    private static PreparedStatement psForNextId,psForUserName,psForCity,
            psForInsertCandidate,psForCandidateDetails,psForViewCandidate;
    private static Statement st;
    static{
        try{
            psForNextId = DBConnection.getConnection().prepareStatement("SELECT max(candidate_id) from candidate");
            psForUserName = DBConnection.getConnection().prepareStatement("SELECT username from user_details where ctzn_no = ?");
            psForCity = DBConnection.getConnection().prepareStatement("Select distinct city from user_details");
            psForInsertCandidate = DBConnection.getConnection().prepareStatement("Insert into candidate values (?,?,?,?,?)");
            st = DBConnection.getConnection().createStatement();
            psForCandidateDetails = DBConnection.getConnection().prepareStatement("Select * from candidate where candidate_id = ?");
            psForViewCandidate = DBConnection.getConnection().prepareStatement("select candidate_id, username,party, symbol from candidate,user_details where candidate.user_id = user_details.ctzn_no and candidate.city = (select city from user_details where ctzn_no = ?)");
            
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    public static String getNewId() throws SQLException{
        ResultSet rs = psForNextId.executeQuery();
        rs.next();
        String cid = rs.getString(1);
        if(cid == null){
            return "C101";
        }
        return "C" + (Integer.parseInt(cid.substring(1)) + 1);
    }
    
    public static String getUserNameById(String userId) throws SQLException{
        psForUserName.setString(1, userId);
        ResultSet rs = psForUserName.executeQuery();
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
    
    public static List<String> getCities() throws SQLException{
        List<String> cityList = new ArrayList<>();
        ResultSet rs = psForCity.executeQuery();
        while(rs.next()){
            cityList.add(rs.getString(1));
        }
        return cityList;
    }
    
    
    public static boolean addCandidate(CandidateDTO candidate)throws SQLException{
        psForInsertCandidate.setString(1, candidate.getCandidateId());
        psForInsertCandidate.setString(2, candidate.getParty());
        psForInsertCandidate.setString(3, candidate.getUserId());
        psForInsertCandidate.setBinaryStream(4, candidate.getSymbol());
        psForInsertCandidate.setString(5, candidate.getCity());
        return psForInsertCandidate.executeUpdate()!=-1;
    }
    
    public static List<String> getCandidateId() throws SQLException{
        List<String> candidateIdList = new ArrayList<>();
        ResultSet rs = st.executeQuery("select candidate_id from candidate");
        while(rs.next()){
            candidateIdList.add(rs.getString(1));
        }
        return candidateIdList;
    }
    
    public static CandidateDetailsDTO getCandidateDetailsById(String id)throws SQLException,IOException{
        psForCandidateDetails.setString(1, id);
        CandidateDetailsDTO cdd = new CandidateDetailsDTO();
        ResultSet rs = psForCandidateDetails.executeQuery();
        
        // Converting image into Base64 String
        // blob-> inputStream->(send data in) outputStream -(get) bytesArray -> (convert) base64String
        Blob blob;
        InputStream inputStream;
        byte[] buffer;
        byte[] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        
        if(rs.next()){
            blob = rs.getBlob(4);
            inputStream = blob.getBinaryStream();
            outputStream = new ByteArrayOutputStream();
            buffer = new byte[4096];
            bytesRead = -1;
            while((bytesRead = inputStream.read(buffer))!=-1){            
                outputStream.write(buffer,0,bytesRead);
            }
            imageBytes = outputStream.toByteArray();
            Base64.Encoder en = Base64.getEncoder();
            base64Image = en.encodeToString(imageBytes);
            // image is converted into string finished
            
            cdd.setSymbol(base64Image);
            cdd.setCandidateId(id);
            cdd.setCandidateName(getUserNameById(rs.getString(3)));
            cdd.setParty(rs.getString(2));
            cdd.setCity(rs.getString(5));
            cdd.setUserId(rs.getString(3));    
        }
        return cdd;
    }
    
    
//    Fetching record of those candidate whose city is same as current city
    public static List<CandidateInfoDTO> viewCandiate(String ctzn_no)throws SQLException, IOException{
        List<CandidateInfoDTO> candidateList = new ArrayList<>();
        psForViewCandidate.setString(1, ctzn_no);
        ResultSet rs = psForViewCandidate.executeQuery();
        
        Blob blob;
        InputStream inputStream;
        byte[] buffer;
        byte[] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        
        while(rs.next()){
            blob = rs.getBlob(4);
            inputStream = blob.getBinaryStream();
            outputStream = new ByteArrayOutputStream();
            buffer = new byte[4096];
            bytesRead = -1;
            while((bytesRead = inputStream.read(buffer))!=-1){            
                outputStream.write(buffer,0,bytesRead);
            }
            imageBytes = outputStream.toByteArray();
            Base64.Encoder en = Base64.getEncoder();
            base64Image = en.encodeToString(imageBytes);
            
            CandidateInfoDTO candidate = new CandidateInfoDTO();
            candidate.setCandidateId(rs.getString(1));
            candidate.setCandidateName(rs.getString(2));
            candidate.setParty(rs.getString(3));
            candidate.setSymbol(base64Image);
            candidateList.add(candidate);
        }
        return candidateList;
    }
}


