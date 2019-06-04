<div class="container">

	<div class="row">

		<!-- to display the actual products -->
		<div class="col-md-9">
			<!-- Added breadcrumb component -->
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProducts == true}">			
						<script>
							window.categoryId = '';
						</script>		
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">View Customers</li>
						</ol>
					</c:if>
				</div>
			</div>

			<!-- code for data table -->
			<div class="row">			
				<div class="col-xs-12">
					<table id="customerListTable" class="table table-striped table-borderd">
						<thead>
							<tr>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Email</th>
								<th>Customer Number</th>
								<th>Activate</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			
		</div>

	</div>
</div>