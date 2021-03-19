
	<div class="form-group"> <!-- Full Name -->
		<label for="full_name_id" class="control-label">Full Name</label>
		<input type="text" class="form-control" id="inputFullName" name="inputFullName" placeholder="John Deer">
	</div>	

	<div class="form-group"> <!-- Street 1 -->
		<label for="street1_id" class="control-label">Street Address 1</label>
		<input type="text" class="form-control" id="inputStreet" name="inputStreet" placeholder="Street address, P.O. box, company name, c/o">
	</div>					
							
	<div class="form-group"> <!-- Street 2 -->
		<label for="street2_id" class="control-label">Street Address 2</label>
		<input type="text" class="form-control" id="inputStreet2" name="inputStreet2" placeholder="Apartment, suite, unit, building, floor, etc.">
	</div>	

	<div class="form-group"> <!-- City-->
		<label for="city_id" class="control-label">City</label>
		<input type="text" class="form-control" id="inputCity" name="inputCity" placeholder="Smallville">
	</div>									
							
	<div class="form-group"> <!-- State Button -->
		<label for="state_id" class="control-label">State</label>
		<select class="form-control" id="inputState">
			<option value="AL">Alabama</option>
			<option value="AK">Alaska</option>
			<option value="AZ">Arizona</option>
			<option value="AR">Arkansas</option>
			<option value="CA">California</option>
			<option value="CO">Colorado</option>
			<option value="CT">Connecticut</option>
			<option value="DE">Delaware</option>
			<option value="DC">District Of Columbia</option>
			<option value="FL">Florida</option>
			<option value="GA">Georgia</option>
			<option value="HI">Hawaii</option>
			<option value="ID">Idaho</option>
			<option value="IL">Illinois</option>
			<option value="IN">Indiana</option>
			<option value="IA">Iowa</option>
			<option value="KS">Kansas</option>
			<option value="KY">Kentucky</option>
			<option value="LA">Louisiana</option>
			<option value="ME">Maine</option>
			<option value="MD">Maryland</option>
			<option value="MA">Massachusetts</option>
			<option value="MI">Michigan</option>
			<option value="MN">Minnesota</option>
			<option value="MS">Mississippi</option>
			<option value="MO">Missouri</option>
			<option value="MT">Montana</option>
			<option value="NE">Nebraska</option>
			<option value="NV">Nevada</option>
			<option value="NH">New Hampshire</option>
			<option value="NJ">New Jersey</option>
			<option value="NM">New Mexico</option>
			<option value="NY">New York</option>
			<option value="NC">North Carolina</option>
			<option value="ND">North Dakota</option>
			<option value="OH">Ohio</option>
			<option value="OK">Oklahoma</option>
			<option value="OR">Oregon</option>
			<option value="PA">Pennsylvania</option>
			<option value="RI">Rhode Island</option>
			<option value="SC">South Carolina</option>
			<option value="SD">South Dakota</option>
			<option value="TN">Tennessee</option>
			<option value="TX">Texas</option>
			<option value="UT">Utah</option>
			<option value="VT">Vermont</option>
			<option value="VA">Virginia</option>
			<option value="WA">Washington</option>
			<option value="WV">West Virginia</option>
			<option value="WI">Wisconsin</option>
			<option value="WY">Wyoming</option>
		</select>					
	</div>
	
	<div class="form-group"> <!-- Zip Code-->
		<label for="zip_id" class="control-label">Zip Code</label>
		<input type="text" class="form-control" id="inputZip" name="inputZip" placeholder="#####">
	</div>	
	
	 <label class="checkbox" for="billinginformation">
                      <input type="checkbox" name="billinginformation" id="billinginformation" value="Use Shipping Address" checked>
                     Same as Billing
                    </label>
     <input type="submit" class="form-btn" name="action"  value="calculateShipping"/>	