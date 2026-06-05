## MySQL Database Design

### Table: patients
- id: INT, Primary Key, Unique, Auto Increment
- first_name: Text(100)
- last_name: Text(100)
- address: Text(100)
- username: Text(100), Unique
- password: Text(100), Unique
- doctor_id: Foreign Key → doctors(id)

### Table: doctors
- id: INT, Primary Key, Unique, Auto Increment
- first_name: Text(100)
- last_name: Text(100)
- username: Text(100), Unique
- password: Text(100), Unique
- specialty: Text(100)
- appointments_id: Foreign Key → appointments(id)
- patient_id: INT, Foreign Key → patients(id)

### Table: admin
- id: INT, Primary Key, Unique, Auto Increment
- first_name: Text(100)
- last_name: Text(100)
- username: Text(100), Unique
- password: Text(100), Unique

### Table: appointments
- id: INT, Primary Key, Unique, Auto Increment
- doctor_id: INT, Foreign Key → doctors(id)
- patient_id: INT, Foreign Key → patients(id)
- appointment_time: DATETIME, Unique, Not Null
- appointment_date: DATE, Unique, Not Null
- note: Text(255)
- status: INT (0 = Scheduled, 1 = Completed, 2 = Cancelled)

## MongoDB Collection Design

### Collection: prescriptions
```json
{
  "_id": "ObjectId('64abc123456')",
  "patientName": "John Smith",
  "appointmentId": 51,
  "medication": "Paracetamol",
  "dosage": "500mg",
  "doctorNotes": "Take 1 tablet every 6 hours.",
  "refillCount": 2,
  "doctorName": "Jane Doe",
  "pharmacy": {
    "name": "Walgreens SF",
    "location": "Market Street"
  }
}

### Collection: feedbacks
```json
{
  "_id": "ObjectId('64abc15ed56')",
  "note": "You were great today, and thanks for the advice",
  "appointmentId": 51,
  "patientName": "John Smith",
  "doctorName": "Jane Doe",
}

### Collection: logs
```json
{
  "_id": "ObjectId('64abc145ed56')",
  "messages": [ ObjectMessageId('64abc5ted56')],
  "patientName": "John Smith",
  "doctorName": "Jane Doe",
}

### Collection: messages
```json
{
  "_id": "ObjectId('64abc5ted56')",
  "to": "John Smith",
  "from": "Jane Doe",
  "message": "Thanks for coming in!"
  "logId": 34,
}
