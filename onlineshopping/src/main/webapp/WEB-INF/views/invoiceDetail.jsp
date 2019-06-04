<div class="container">

	<!-- Breadcrumb -->
	<div class="row">
		<div class="col-xs-12">		
			
			<ol class="breadcrumb">
			
				<li><a href="${contextRoot}/home">Home</a></li>
				<li><a href="${contextRoot}/customer/invoice">All Invoices</a></li>
				<li class="active">${title}</li>
			
			</ol>
		
		</div>
	</div>
	
	<div class="row">
		
		<div class="row">
		    	<div class="col-md-12">
		    		<div class="panel panel-default">
		    			<div class="panel-heading">
		    				<h3 class="panel-title"><strong>Invoice Details</strong></h3>
		    			</div>
		    			<div class="panel-body">
		    				<div class="table-responsive">
		    					<table class="table table-condensed">
		    						<thead>
		                                <tr>
		        							<td><strong>Item</strong></td>
		        							<td class="text-center"><strong>Price</strong></td>
		        							<td class="text-center"><strong>Quantity</strong></td>
		        							<td class="text-right"><strong>Totals</strong></td>
		                                </tr>
		    						</thead>
		    						<tbody>
		    							<!-- foreach ($order->lineItems as $line) or some such thing here -->
		    							<c:forEach items="${orderList}" var="orderItem">
			    							<tr>
			    								<td>${orderItem.product.name}</td>
			    								<td class="text-center">&#36; ${orderItem.buyingPrice}</td>
			    								<td class="text-center">${orderItem.productCount}</td>
			    								<td class="text-right">&#36; ${orderItem.total}</td>
			    							</tr>
		    							</c:forEach>
		    						</tbody>
		    					</table>
		    				</div>
		    			</div>
		    		</div>
		    	</div>
		    </div>

	
	</div>

</div>