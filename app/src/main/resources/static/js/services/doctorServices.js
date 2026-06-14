import { API_BASE_URL } from "../config/config.js";
const DOCTOR_API = API_BASE_URL + '/doctor'

export async function getDoctors() {
    try {
        const response = await fetch(DOCTOR_API, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            }
        });

        if (!response.ok) {
            throw new Error('Network response was not ok');
        }

        const data = await response.json();

        return data.doctors || [];
    } catch (error) {
        console.error('Failed to fetch doctors:', error);
        return [];
    }
}

export async function deleteDoctor(id, token) {
    try {
        const response = await fetch(`${DOCTOR_API}/${id}/${token}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        })

        if (!response.ok) {
            throw new Error('Failed to delete doctor, status: ' + response.status);
        }

        const result = await response.json();

        // Assuming the API returns a JSON with success status and message
        return {
            success: result.success || true,
            message: result.message || 'Doctor deleted successfully',
        };
    } catch (error) {
        console.error('Error deleting doctor:', error);
        // Return failure status and error message to avoid frontend crashes
        return {
            success: false,
            message: error.message || 'An error occurred while deleting the doctor',
        }
    }
}

export async function saveDoctor(doctor, token) {
    try {
        let response = fetch(`${DOCTOR_API}/${doctor}/${token}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            }
        })

        const result = await response.json();

        // Assuming the API returns a JSON with success status and message
        return {
            success: result.success || true,
            message: result.message || 'Doctor saved successfully',
        };

    } catch (error) {
        console.error('Error saving doctor:', error);
        // Return failure status and error message to avoid frontend crashes
        return {
            success: false,
            message: error.message || 'An error occurred while saving the doctor',
        }
    }
}

export async function filterDoctors(name, time, specialty) {
    try {
        const response = await fetch(`${PATIENT_API}/${name}/${time}/${specialty}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
            },
        });

        const result = await response.json();

        // Assuming the API returns a JSON with success status and message
        return {
            success: result.success || true,
            message: result.message || 'Doctor saved successfully',
        };
    } catch (error) {
        console.error('Error saving doctor:', error);
        // Return failure status and error message to avoid frontend crashes
        return {
            success: false,
            message: error.message || 'An error occurred while saving the doctor',
        }
    }
}
