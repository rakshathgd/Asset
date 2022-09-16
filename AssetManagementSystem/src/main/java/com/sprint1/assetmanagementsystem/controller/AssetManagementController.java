package com.sprint1.assetmanagementsystem.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sprint1.assetmanagementsystem.exception.LoginException;
import com.sprint1.assetmanagementsystem.exception.NoAccessException;
import com.sprint1.assetmanagementsystem.exception.NotFoundException;
import com.sprint1.assetmanagementsystem.model.Asset;
import com.sprint1.assetmanagementsystem.service.AssetImplementation;

@RestController
//@RequestMapping(path="/api")
@CrossOrigin
public class AssetManagementController {
	// Creating logger Object
	Logger logger = org.slf4j.LoggerFactory.getLogger(AssetManagementController.class);
	@Autowired
	AssetImplementation assetImplementation;

	// Creating and saving Asset
	@PostMapping(path = "/createAsset")
	public ResponseEntity<String> createAsset(@Valid @RequestBody Asset asset)
			throws NotFoundException {
		Asset savedAsset = assetImplementation.createAsset(asset);
		logger.info("Asset Created");
		return new ResponseEntity<>("Asset with the ID" + savedAsset.getAssetId() + "is created", HttpStatus.CREATED);
	}

	// Displaying Assets
	@GetMapping(path = "/viewAsset")
	public ResponseEntity<List<Asset>> viewAsset() throws NotFoundException {
		List<Asset> listOfAsset = assetImplementation.viewAsset();
		for (Asset asset : listOfAsset) {
			logger.info(asset.getAssetId() + " " + asset.getAssetManufacturer() + " "
					+ asset.getShipment().getShipmentId() + " " + asset.getShipment().getShipmentCompanyName());
		}
		return new ResponseEntity<>(listOfAsset, HttpStatus.OK);
	}

	// Removing Assets
	@DeleteMapping(path = "/deleteAsset/{id}")
	public ResponseEntity<String> deleteAsset(@PathVariable int id)
			throws NotFoundException {
		boolean assetRemoved = assetImplementation.deleteAsset(id);
		logger.info("Asset Deleted");
		ResponseEntity<String> response = new ResponseEntity<>("Asset with the ID" + id + "is deleted", HttpStatus.OK);
		if (assetRemoved) {
			return response;
		}
		return null;
	}

	// Updating Assets
	@PutMapping(path = "/updateAsset")
	public String updateAsset(@RequestBody Asset asset)  {
		boolean assetUpdated = assetImplementation.updateAsset(asset);
		logger.info("Asset Updated");

		if (assetUpdated) {
			return "Asset with the ID" + asset.getAssetId() + "is updated";
		}
		return "Not updated";
	}

	// Finding Assets
	@GetMapping(path = "/searchAsset/{id}")
	public Asset getAssetById(@PathVariable int id) throws NotFoundException {

		return assetImplementation.searchAsset(id);
	}

}
