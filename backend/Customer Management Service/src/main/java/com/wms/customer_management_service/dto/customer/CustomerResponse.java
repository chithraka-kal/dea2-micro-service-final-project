package com.wms.customer_management_service.dto.customer;

import com.wms.customer_management_service.enums.CustomerStatus;
import com.wms.customer_management_service.enums.AddressType;
import lombok.Data;

import java.util.List;
import java.util.UUID;

/**
 * DTO for customer API responses.
 */
@Data
public class CustomerResponse {
	private UUID customerId;
	private String customerName;
	private String email;
	private String phone;
	private CustomerStatus status;
	private List<AddressResponse> addresses;

	@Data
	public static class AddressResponse {
		private UUID addressId;
		private AddressType type;
		private String line1;
		private String line2;
		private String city;
		private String district;
		private String postalCode;
		private String country;
	}
}
