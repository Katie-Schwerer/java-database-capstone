function renderHeader() {
    const headerDiv = document.getElementById("header")

    if (window.location.pathname.endsWith("/")) {
        localStorage.removeItem("userRole");
        localStorage.removeItem("token");
        headerDiv.innerHTML = `
          <header class="header">
            <div class="logo-section">
              <img src="../assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
              <span class="logo-title">Hospital CMS</span>
            </div>
          </header>`;
        return;
    }

    const role = localStorage.getItem("userRole");
    const token = localStorage.getItem("token");

    let headerContent = `<header class="header">
         <div class="logo-section">
           <img src="../assets/images/logo/logo.png" alt="Hospital CRM Logo" class="logo-img">
           <span class="logo-title">Hospital CMS</span>
         </div>
        `;

    if ((role === "loggedPatient" || role === "admin" || role === "doctor") && !token) {
        localStorage.removeItem("userRole");
        alert("Session expired or invalid login. Please log in again.");
        window.location.href = "/";
        return;
    } else if (role === "admin") {
        headerContent += `
          <button id="addDocBtn" class="adminBtn" onclick="openModal('addDoctor')">Add Doctor</button>
          <a href="#" onclick="logout()">Logout</a>`;
    } else if (role === "doctor") {
        headerContent += `
          <button class="adminBtn"  onclick="selectRole('doctor')">Home</button>
          <a href="#" onclick="logout()">Logout</a>`;
    } else if (role === "patient") {
        headerContent += `
          <button id="patientLogin" class="adminBtn">Login</button>
          <button id="patientSignup" class="adminBtn">Sign Up</button>`;
    } else if (role === "loggedPatient") {
        headerContent += `
          <button id="home" class="adminBtn" onclick="window.location.href='/pages/loggedPatientDashboard.html'">Home</button>
          <button id="patientAppointments" class="adminBtn" onclick="window.location.href='/pages/patientAppointments.html'">Appointments</button>
          <a href="#" onclick="logoutPatient()">Logout</a>`;
    }

    headerContent += "</header>";
    headerDiv.innerHTML = headerContent;
    attachHeaderButtonListeners();
}

function attachHeaderButtonListeners() {
    const doctorBtn = document.getElementById('doctorBtn');
    const adminBtn = document.getElementById('adminBtn');

    // Select modals
    const doctorModal = document.getElementById('doctorModal');
    const adminModal = document.getElementById('adminModal');

    // Function to open modal
    function openModal(modal) {
        modal.style.display = 'block';  // Or use a class to show modal
    }

    // Add event listeners
    doctorBtn.addEventListener('click', () => {
        openModal(doctorModal);
    });

    adminBtn.addEventListener('click', () => {
        openModal(adminModal);
    });
}

function logout() {
    sessionStorage.clear();
    localStorage.clear();
    window.location.href = "/";
}

function logoutPatient() {
    // Remove only the patient token (assuming stored under 'patientToken')
    sessionStorage.removeItem('patientToken');

    // Redirect to patient dashboard
    window.location.href = '/patient-dashboard';
}

document.addEventListener('DOMContentLoaded', () => {
    renderHeader();
});

