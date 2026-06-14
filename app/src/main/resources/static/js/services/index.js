import openModal from '../components/modals.js'
import API_BASE_URL from '../config/config.js'

const ADMIN_API = API_BASE_URL + '/admin';
const DOCTOR_API = API_BASE_URL + '/doctor/login'

window.onload = function () {
    const adminBtn = document.getElementById('adminLogin');
    const doctorBtn = document.getElementById('doctorLogin')
    if (adminBtn) {
        adminBtn.addEventListener('click', () => {
            openModal('adminLogin');
        });
    } else if (doctorBtn) {
        doctorBtn.addEventListener('click', () => {
            openModal('doctorLogin');
        })
    }
}

export async function adminLoginHandler() {
    try {
        const admin = { username, password };
        await fetch(ADMIN_API, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(admin)
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok')
            }
    
            return response.json()
        }).then(result => {
            localStorage.setItem('token', result.token)
            selectRole('admin');
        }).catch(error => {
            alert("Invalid credentials!", error)
        });
    } catch (error) {
        console.error('Fetch error:', error);
    }
}

export async function doctorLoginHandler() {
    try {
        const doctor = { username, password };
        await fetch(ADMIN_API, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(doctor)
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok')
            }
    
            return response.json()
        }).then(result => {
            localStorage.setItem('token', result.token)
            selectRole('doctor');
        }).catch(error => {
            alert("Invalid credentials!", error)
        });
    } catch (error) {
        console.error('Fetch error:', error);
    }
}



/*


  Define a function named doctorLoginHandler on the global window object
  This function will be triggered when a doctor submits their login credentials

  Step 1: Get the entered email and password from the input fields
  Step 2: Create a doctor object with these credentials

  Step 3: Use fetch() to send a POST request to the DOCTOR_API endpoint
    - Include headers and request body similar to admin login

  Step 4: If login is successful:
    - Parse the JSON response to get the token
    - Store the token in localStorage
    - Call selectRole('doctor') to proceed with doctor-specific behavior

  Step 5: If login fails:
    - Show an alert for invalid credentials

  Step 6: Wrap in a try-catch block to handle errors gracefully
    - Log the error to the console
    - Show a generic error message
*/
