package cafytech.projectManagementDemo.dao;


import cafytech.projectManagementDemo.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ClientDAO extends JpaRepository<Client, Integer> {

     Client findBycompanyName(String companyName);


   //  @Modifying
   //  @Query("update SubmittedInfo s set s.fileData=?1 where s.path = ?2")
   //  void setFileDataByFileName(String fileData, String filePath);

     @Query(value = "select c.companyName from Client c where c.clientID=?1")
     String findByclientID(int clientID);  //return string type not an object list


}
