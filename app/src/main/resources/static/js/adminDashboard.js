import openModal from '../components/models.js'
import { getDoctors, filterDoctors, saveDoctor } from "./services/doctorServices.js"
import createDoctorCard from "./components/doctorCard.js"

document.addEventListener("DOMContentLoaded", () => {
    loadDoctorCards();
});

document.getElementById('addDocBtn').addEventListener('click', () => {
    openModal('addDoctor');
});

function loadDoctorCards() {
    getDoctors()
        .then(doctors => {
            const contentDiv = document.getElementById("content");
            contentDiv.innerHTML = "";

            doctors.forEach(doctor => {
                const card = createDoctorCard(doctor);
                contentDiv.appendChild(card);
            });
        })
        .catch(error => {
            console.error("Failed to load doctors:", error);
        });
}

document.getElementById("searchBar").addEventListener("input", filterDoctorsOnChange);
document.getElementById("filterTime").addEventListener("change", filterDoctorsOnChange);
document.getElementById("filterSpecialty").addEventListener("change", filterDoctorsOnChange);

function filterDoctorsOnChange() {
    const searchBar = document.getElementById("searchBar").value.trim();
    const filterTime = document.getElementById("filterTime").value;
    const filterSpecialty = document.getElementById("filterSpecialty").value;


    const name = searchBar.length > 0 ? searchBar : null;
    const time = filterTime.length > 0 ? filterTime : null;
    const specialty = filterSpecialty.length > 0 ? filterSpecialty : null;

    filterDoctors(name, time, specialty)
        .then(response => {
            const doctors = response.doctors;
            const contentDiv = document.getElementById("content");
            contentDiv.innerHTML = "";

            if (doctors.length > 0) {
                console.log(doctors);
                doctors.forEach(doctor => {
                    const card = createDoctorCard(doctor);
                    contentDiv.appendChild(card);
                });
            } else {
                contentDiv.innerHTML = "<p>No doctors found with the given filters.</p>";
                console.log("Nothing");
            }
        })
        .catch(error => {
            console.error("Failed to filter doctors:", error);
            alert("An error occurred while filtering doctors.");
        });
}

export function renderDoctorCards(doctors) {
    const contentDiv = document.getElementById("content");
    contentDiv.innerHTML = "";

    doctors.forEach(doctor => {
        const card = createDoctorCard(doctor);
        contentDiv.appendChild(card);
    });
}

export async function adminAddDoctor() {
    openModel("addDocter")
    const token = localStorage.getItem('token');
    if (!token) {
        alert('You must be logged in as admin to add a doctor.');
        return;
    }

    // Collect form values
    const name = document.getElementById('doctorName').value;
    const specialty = document.getElementById('doctorSpecialty').value;
    const email = document.getElementById('doctorEmail').value;
    const password = document.getElementById('doctorPassword').value;
    const mobile = document.getElementById('doctorMobile').value;

    // Collect availability checkboxes (example)
    const availabilityCheckboxes = document.querySelectorAll('input[name="availability"]:checked');
    const availability = Array.from(availabilityCheckboxes).map(cb => cb.value);

    const doctorData = {
        name,
        specialty,
        email,
        password,
        mobile,
        availability
    };

    try {
        const result = await saveDoctor(doctorData, token);

        if (result.success) {
            closeModal();
            alert('Doctor added successfully!');
            // Reload or refresh doctor list, e.g.:
            loadDoctors();
        } else {
            alert('Failed to add doctor: ' + result.message);
        }
    } catch (error) {
        alert('Error adding doctor: ' + error.message);
    }

}

/*

  Function: adminAddDoctor
  Purpose: Collect form data and add a new doctor to the system

    Collect input values from the modal form
    - Includes name, email, phone, password, specialty, and available times

    Retrieve the authentication token from localStorage
    - If no token is found, show an alert and stop execution

    Build a doctor object with the form values

    Call saveDoctor(doctor, token) from the service

    If save is successful:
    - Show a success message
    - Close the modal and reload the page

    If saving fails, show an error message
*/
