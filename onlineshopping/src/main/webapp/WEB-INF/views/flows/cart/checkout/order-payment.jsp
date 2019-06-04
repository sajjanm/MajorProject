<%@include file="../../flows-shared/header.jsp" %>
<div class="container">

	<div class="row">
			<!--  To display all the goods -->
			<div class="col-md-6 grand-total">
				
				<div class="row">
					<c:forEach items="${checkoutModel.cartLines}" var="cartLine">
					<div class="col-xs-12">
						
						<div>
							<h3>${cartLine.product.name}</h3>
							<hr/>
							<h4>Quantity -${cartLine.productCount}</h4>
							<h5>Buying Price - &#36; ${cartLine.buyingPrice}/-</h5>							
						</div>						
						<hr/>
						<div class="text-right">
							<h3>Grand Total - &#36; ${cartLine.total}/-</h3>
						</div>						
					</div>
					</c:forEach>
				</div>
				
				
			</div>
			
			<div class="col-md-6 payment">
	            <div class="panel panel-default">
	                <div class="panel-heading">
	                    <h3 class="panel-title">
	                        Payment Details
	                    </h3>
	                </div>
	                
	                <ul class="nav nav-pills nav-stacked">
		                <li class="active"><a href="#"><span class="badge pull-right"> &#36; ${checkoutModel.checkoutTotal}/-</span> Final Payment Amount</a></li>
		            </ul>
		            <br/>
		            
		            <ul class="nav nav-pills">
					  <li class="active"><a data-toggle="pill" href="#card">Pay By Card</a></li>
					  <li><a data-toggle="pill" href="#paypal">Pay by Paypal</a></li>
					</ul>
					
					<div class="tab-content">
					
					  <div id="card" class="tab-pane fade in active">
					    <div class="panel-body">
		                    <form role="form">
			                    <div class="form-group">
			                        <label for="cardNumber"> CARD NUMBER</label>
			                        <div class="input-group">
			                            <input type="text" class="form-control" id="cardNumber" placeholder="Valid Card Number"
			                                required autofocus />
			                            <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
			                        </div>
			                    </div>
			                    <div class="row">
			                        <div class="col-xs-7 col-md-7">
			                            <div class="form-group">
			                                <label for="expityMonth">EXPIRY DATE</label>
			                                <br/>
			                                <div class="col-xs-6 col-lg-6 pl-ziro">
			                                    <input type="text" class="form-control" id="expityMonth" placeholder="MM" required />
			                                </div>
			                                <div class="col-xs-6 col-lg-6 pl-ziro">
			                                    <input type="text" class="form-control" id="expityYear" placeholder="YY" required /></div>
			                            </div>
			                        </div>
			                        <div class="col-xs-5 col-md-5 pull-right">
			                            <div class="form-group">
			                                <label for="cvCode"> CV CODE</label>
			                                <input type="password" class="form-control" id="cvCode" placeholder="CV" required />
			                            </div>
			                        </div>
			                    </div>
             			        <a href="${flowExecutionUrl}&_eventId_pay" class="btn btn-warning btn-lg btn-block" role="button">Pay</a>
		                    </form>
		                </div>
		       			            
					  </div>
					  
					  <div id="paypal" class="tab-pane fade">
			    	    <!-- Set up a container element for the button -->
			    		<div id="paypal-button-container"></div>
					  </div>
					</div>
	                
	                
	            </div>

			    <!-- Include the PayPal JavaScript SDK -->
			    <script src="https://www.paypal.com/sdk/js?client-id=AXTodRfGiotiLk0cDsFNsBWIiiZeto_wiSTpNSbcdvYj4_ZpTMcm1Wh-31nNmxRXg0G93aBFE4MK4pzO&currency=AUD&disable-card=amex,visa,mastercard"></script>
			
			    <script>
			        // Render the PayPal button into #paypal-button-container
			         paypal.Buttons({
					    createOrder: function(data, actions) {
					      // Set up the transaction
					      return actions.order.create({
					        purchase_units: [{
					          amount: {
					            value: '${checkoutModel.checkoutTotal}'
					          }
					        }]
					      });
					    },
					    onApprove: function(data, actions) {
					        return actions.order.capture().then(function(details) {
					          alert('Transaction completed by ' + details.payer.name.given_name);
					          // Call your server to save the transaction
					          return fetch('/paypal-transaction-complete', {
					            method: 'post',
					            headers: {
					              'content-type': 'application/json'
					            },
					            body: JSON.stringify({
					              orderID: data.orderID
					            })
					          });
					        });
					      }
					  }).render('#paypal-button-container');
			    </script>
			</div>

	</div>
</div>
<%@include file="../../flows-shared/footer.jsp" %>