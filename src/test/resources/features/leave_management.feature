#Author: Alexander Valencia Delgado #Language: en

Feature: Gestión de Permisos en OrangeHRM
  Como usuario del sistema OrangeHRM
  Quiero poder gestionar los permisos de los empleados
  Para llevar un control adecuado de las ausencias

  Background:
    Given que estoy en la página de login de OrangeHRM
    When ingreso el usuario "Admin" y la contraseña "admin123"
    And hago clic en el botón "Login"
    Then debo estar autenticado y redirigido a la página principal

  Scenario: Ver la lista de permisos
    Given que estoy en la página de "Leave List"
    When la página se carga completamente
    Then debo ver los filtros "From Date", "To Date", "Show Leave with Status", "Leave Type", "Employee Name", "Sub Unit", e "Include Past Employees"
    And debo ver las columnas de la tabla "Date", "Employee Name", "Leave Type", "Leave Balance (Days)", "Number of Days", "Status", "Comments", y "Actions"
    And si no hay registros, debo ver el mensaje "No Records Found"
