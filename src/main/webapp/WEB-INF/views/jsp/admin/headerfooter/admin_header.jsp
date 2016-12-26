<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>

<script>
	function myFunction(element, index) {
		var input, filter, table, tr, td, i;
		input = document.getElementById(element);
		filter = input.value.toUpperCase();
		table = document.getElementById("myTable");
		tr = table.getElementsByTagName("tr");
		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[index];
			if (td) {
				if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}
</script>

<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="/admin/main" class="navbar-brand"><i class="fa fa-home"></i></a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/admin/positions">Positions</a></li>
			<li><a href="/admin/employees">Employees</a></li>
			<li><a href="/admin/ingredients">Ingredients</a></li>
			<li><a href="/admin/warehouses">Warehouses</a></li>
			<li><a href="/admin/dishes">Dishes</a></li>
			<li><a href="/admin/menus">Menus</a></li>
			<li><a href="/admin/categories">Categories</a></li>
			<li><a href="/admin/dishes_preparations">DishesPreparations</a></li>
			<li><a href="/admin/desks">Desk</a></li>
			<li><a href="/admin/orders">Orders</a></li>


		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
		</ul>
	</div>
</nav>

<style>
	.round1 {
		border-radius: 85px; /* Радиус скругления */
		border: 1px solid; /* Параметры рамки */
		box-shadow: 0 0 7px #666; /* Параметры тени */
	}
</style>