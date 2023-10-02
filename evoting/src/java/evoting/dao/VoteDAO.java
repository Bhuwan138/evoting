/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateInfoDTO;
import evoting.dto.VoteDTO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Bhuwan Pandey
 */
public class VoteDAO {
    private static PreparedStatement psForCandidateByVoter,psForCandidateInfoByCandidateId
            ,psForAddVote,psForResult;
    private static Statement voteCount;
    static{
        try{
            psForCandidateByVoter = DBConnection.getConnection().prepareStatement("SELECT candidate_id from voting where voter_id = ?");            
            psForCandidateInfoByCandidateId = DBConnection.getConnection().prepareStatement("Select candidate_id,username,party,symbol FROM candidate,user_details where candidate.user_id=user_details.ctzn_no and candidate.candidate_id = ?");
            psForAddVote = DBConnection.getConnection().prepareStatement("insert into voting values(?,?)");
            psForResult = DBConnection.getConnection().prepareStatement("Select candidate_id,count(*) as vote_obt from voting group by candidate_id order by vote_obt desc");
            voteCount = DBConnection.getConnection().createStatement();

        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    
    public static String getCandidateId(String userId)throws SQLException{
        psForCandidateByVoter.setString(1, userId);
        ResultSet rs = psForCandidateByVoter.executeQuery();
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
    
    public static CandidateInfoDTO getVote(String candidateID)throws SQLException,IOException{
        psForCandidateInfoByCandidateId.setString(1, candidateID);
        CandidateInfoDTO ci = new CandidateInfoDTO();
        ResultSet rs = psForCandidateInfoByCandidateId.executeQuery();
        
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
            
            
            ci.setCandidateId(rs.getString(1));
            ci.setCandidateName(rs.getString(2));
            ci.setParty(rs.getString(3));
            ci.setSymbol(base64Image);
        }
        return ci;
    }
        
    public static boolean addVote(VoteDTO vote)throws SQLException{
        psForAddVote.setString(1, vote.getCandidateId());
        psForAddVote.setString(2, vote.getVoterId());
        return psForAddVote.executeUpdate()==1;
    }
    
       
    public static Map<String,Integer> getResult()throws SQLException{
        Map<String,Integer> result = new LinkedHashMap<>();
        ResultSet rs = psForResult.executeQuery();
        while(rs.next()){
            result.put(rs.getString(1), rs.getInt(2));
        }
        return  result;
    }

    public static int getVoteCount()throws SQLException{
        ResultSet rs = voteCount.executeQuery("select count(*) from voting");
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
}
