<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container">

	<c:if test="${not empty message}">	
		<div class="row">			
			<div class="col-xs-12 col-md-offset-2 col-md-8">			
				<div class="alert alert-info fade in">${message}</div>				
			</div>
		</div>
	</c:if>

	<div class="row">

		<div class="col-md-offset-2 col-md-8">

			<div class="panel panel-primary">

				<div class="panel-heading">
					<h4>Upload Product</h4>
				</div>

				<div class="panel-body">
					<sf:form class="form-horizontal" modelAttribute="nexcelFIle" action="${contextRoot}/manage/upload" method="POST" enctype="multipart/form-data">
												
						<div class="form-group">
							<label class="control-label col-md-4">Upload a file</label>
							<div class="col-md-8">
								<sf:input type="file" path="file" class="form-control"/>
								<sf:errors path="file" cssClass="help-block" element="em"/> 
							</div>
						</div>
					
						<div class="form-group">
							
							<div class="col-md-offset-4 col-md-4">
							
								<input type="submit" name="submit" value="Upload" class="btn btn-primary"/>
								
							</div>
						</div>						
										
					</sf:form>

				</div>   

				
				<!--  <c:url value="/upload/uploadExcelFile" var="uploadFileUrl" />
				<form method="post" enctype="multipart/form-data"
				  action="${uploadFileUrl}">
				    <input type="file" name="file" accept=".xls,.xlsx" /> <input
				      type="submit" value="Upload file" />
				</form>
				 -->
			</div>

		</div>

	</div>

	

</div>