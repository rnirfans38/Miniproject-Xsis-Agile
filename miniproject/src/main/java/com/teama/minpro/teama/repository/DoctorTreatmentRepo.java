//package minpro289.repositories;
//
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import minpro289.models.T_doctor_treatment;
//
//public interface DoctorTreatmentRepo extends JpaRepository<T_doctor_treatment, Long>{
//	
//	@Query(value = "select id, name from t_doctor_treatment where is_delete = false and id between 1 and 17",nativeQuery = true)
//	List <Map<String, Object>> listTreatment();
// 	
//	@Query(value = "select id, name from t_doctor_treatment where id = ?1 ",nativeQuery = true)
//	Map<String, Object> listTreatmentById(long id);
//
//}
