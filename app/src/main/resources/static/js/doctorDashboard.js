import getAllAppointments from './services/appointmentRecordService.js'
import createPatientRow from './components/patientRows.js';

let patientTable = document.getElementById('patientTableBody');
let selectedDate = Date.now()
let token = localStorage.getItem("token")
let patientName = null;

document.getElementById("searchBar").addEventListener('input', (event) => {
    if (event.target.value === "") {
        patientName = null;
    }

    patientName = event.value.target;
    loadAppointments();
})

document.getElementById('todayButton').addEventListener('click', () => {
    selectedDate = Date.now();
    const datePicker = document.getElementById('datePicker'); // Replace with your date picker ID
    datePicker.value = selectedDate;
    loadAppointments()
})

document.getElementById('datePicker').addEventListener('change', (event) => {
    selectedDate = event.target.value
    loadAppointments()
})

async function loadAppointments() {
  try {
    // Fetch appointments
    const appointments = await getAllAppointments(selectedDate, patientName, token);
    
    // Clear existing table content
    patientTable.innerHTML = '';
    
    if (!appointments || appointments.length === 0) {
      // No appointments found
      const noDataRow = document.createElement('tr');
      const noDataCell = document.createElement('td');
      noDataCell.colSpan = 5; // Adjust colspan as needed
      noDataCell.textContent = 'No Appointments found for today';
      noDataRow.appendChild(noDataCell);
      patientTable.appendChild(noDataRow);
    } else {
      // Appointments exist - create rows for each
      appointments.forEach(appointment => {
        const patientRow = createPatientRow(appointment);
        patientTable.appendChild(patientRow);
      });
    }
  } catch (error) {
    // In case of error, show fallback message
    patientTable.innerHTML = '';
    const errorRow = document.createElement('tr');
    const errorCell = document.createElement('td');
    errorCell.colSpan = 5; // Adjust colspan as needed
    errorCell.textContent = 'Error loading appointments. Please try again later.';
    errorRow.appendChild(errorCell);
    patientTable.appendChild(errorRow);
  }
}

document.addEventListener("DOMContentLoaded", () => {
  loadAppointments()
});
