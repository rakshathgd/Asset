package com.sprint1.assetmanagementsystem.repository;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sprint1.assetmanagementsystem.model.Maintain;

@Repository
public interface MaintainRepository extends JpaRepository<Maintain,Integer> {
	@Modifying
	@Transactional
	@Query(value="update maintain set asset_model=:model,asset_type=:type,asset_manufacturer=:manufacturer,asset_movement_date=:movement_date,"
			+ "asset_status=:status,asset_source_location=:source_location,asset_destination_location=:destination_location,user_name=:user_name,"
			+ "shipment_company_name=:company_name,shipment_sub_location=:sub_location,shipment_company_state=:state,shipment_company_country=:country where asset_asset_id=:id",nativeQuery=true)
	public void updateMaintain(@Param("id")int asset_id,@Param("model") String asset_model,@Param("type")String asset_type, @Param("manufacturer")String asset_Manufacturer,
			@Param("movement_date") LocalDate asset_Movement_date,@Param("status") String asset_status,@Param("source_location") String asset_source_location,
			@Param("destination_location")String asset_destination_location,@Param("user_name") String user_name,@Param("company_name") String shipment_company_name,
			@Param("sub_location")	String subLocation,@Param("state") String shipment_state,@Param("country") String shipment_country);
	
}
