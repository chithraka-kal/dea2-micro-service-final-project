package com.wms.customer_management_service.dto.customer;


import com.wms.customer_management_service.enums.AddressType;
import lombok.Data;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * DTO for customer registration/update requests.
 */
@Data
public class CustomerRequest {
	@NotBlank(message = "Customer name is required")
	private String customerName;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Phone is required")
	private String phone;

	@NotNull(message = "Addresses are required")
	private List<AddressRequest> addresses;

	@Data
	public static class AddressRequest {
		@NotNull(message = "Address type is required")
		private AddressType type;

		@NotBlank(message = "Line1 is required")
		private String line1;

		private String line2;

		@NotBlank(message = "City is required")
		private String city;

		@NotBlank(message = "District is required")
		private String district;

		@NotBlank(message = "Postal code is required")
		private String postalCode;

		@NotBlank(message = "Country is required")
		private String country;
	}
}
