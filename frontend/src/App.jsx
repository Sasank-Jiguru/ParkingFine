
import { useEffect, useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

const API_URL = 'http://localhost:8083/parkingfines';

function App() {
  const [fines, setFines] = useState([]);
  const [fineNumber, setFineNumber] = useState('');
  const [vehicleNumber, setVehicleNumber] = useState('');
  const [amount, setAmount] = useState('');
  const [paid, setPaid] = useState(false);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  // Display all parking fines
  const loadFines = async () => {
    try {
      const response = await axios.get(`${API_URL}/displayall`);
      setFines(response.data);
      setError('');
    } catch (err) {
      console.error('Load fines error:', err);
      setError('Unable to load parking fines. Check backend connection.');
    }
  };

  useEffect(() => {
    loadFines();
  }, []);

  // Add a parking fine
  const addFine = async (e) => {
    e.preventDefault();

    setMessage('');
    setError('');

    if (
      !fineNumber.trim() ||
      !vehicleNumber.trim() ||
      amount === '' ||
      Number(amount) <= 0
    ) {
      setError('Please enter valid details in all required fields.');
      return;
    }

    setLoading(true);

    try {
      await axios.post(`${API_URL}/add`, {
        fineNumber: fineNumber.trim(),
        vehicleNumber: vehicleNumber.trim(),
        amount: Number(amount),
        paid: paid,
      });

      setMessage('Parking fine added successfully!');

      setFineNumber('');
      setVehicleNumber('');
      setAmount('');
      setPaid(false);

      await loadFines();
    } catch (err) {
      console.error('Add fine error:', err);

      setError(
        typeof err.response?.data === 'string'
          ? err.response.data
          : err.response?.data?.message ||
              'Failed to add parking fine.'
      );
    } finally {
      setLoading(false);
    }
  };

  // Delete a parking fine
  const deleteFine = async (id) => {
    if (!window.confirm('Are you sure you want to delete this parking fine?')) {
      return;
    }

    setMessage('');
    setError('');

    try {
      await axios.delete(`${API_URL}/delete/${id}`);

      setMessage('Parking fine deleted successfully!');

      await loadFines();
    } catch (err) {
      console.error('Delete fine error:', err);
      setError('Failed to delete parking fine.');
    }
  };

  return (
    <div className="app-background">
      <nav className="navbar navbar-dark bg-primary shadow-sm">
        <div className="container">
          <span className="navbar-brand fw-bold">
            Parking Fine Management
          </span>
        </div>
      </nav>

      <main className="container py-4">
        <div className="row justify-content-center">
          <div className="col-lg-10">

            {message && (
              <div className="alert alert-success">
                {message}
              </div>
            )}

            {error && (
              <div className="alert alert-danger">
                {error}
              </div>
            )}

            {/* Add Parking Fine Form */}
            <div className="card shadow-sm mb-4">
              <div className="card-header bg-white">
                <h4 className="mb-0">Add Parking Fine</h4>
              </div>

              <div className="card-body">
                <form onSubmit={addFine}>
                  <div className="mb-3">
                    <label className="form-label">Fine Number</label>
                    <input
                      type="text"
                      className="form-control"
                      value={fineNumber}
                      onChange={(e) => setFineNumber(e.target.value)}
                      placeholder="Example: PF101"
                      required
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Vehicle Number</label>
                    <input
                      type="text"
                      className="form-control"
                      value={vehicleNumber}
                      onChange={(e) => setVehicleNumber(e.target.value)}
                      placeholder="Example: AP07AB1234"
                      required
                    />
                  </div>

                  <div className="mb-3">
                    <label className="form-label">Fine Amount (₹)</label>
                    <input
                      type="number"
                      className="form-control"
                      min="0.01"
                      step="0.01"
                      value={amount}
                      onChange={(e) => setAmount(e.target.value)}
                      required
                    />
                  </div>

                  <div className="form-check mb-3">
                    <input
                      type="checkbox"
                      className="form-check-input"
                      id="paid"
                      checked={paid}
                      onChange={(e) => setPaid(e.target.checked)}
                    />

                    <label className="form-check-label" htmlFor="paid">
                      Fine Paid
                    </label>
                  </div>

                  <button
                    type="submit"
                    className="btn btn-primary"
                    disabled={loading}
                  >
                    {loading ? 'Adding...' : 'Add Parking Fine'}
                  </button>
                </form>
              </div>
            </div>

            {/* Parking Fine Records */}
            <div className="card shadow-sm">
              <div className="card-header bg-white d-flex justify-content-between align-items-center">
                <h4 className="mb-0">Parking Fine Records</h4>

                <button
                  type="button"
                  className="btn btn-outline-primary btn-sm"
                  onClick={loadFines}
                >
                  Refresh
                </button>
              </div>

              <div className="card-body">
                {fines.length === 0 ? (
                  <p className="text-muted mb-0">
                    No parking fines found.
                  </p>
                ) : (
                  <div className="table-responsive">
                    <table className="table table-striped table-hover align-middle">
                      <thead className="table-primary">
                        <tr>
                          <th>ID</th>
                          <th>Fine Number</th>
                          <th>Vehicle Number</th>
                          <th>Amount</th>
                          <th>Paid</th>
                          <th>Action</th>
                        </tr>
                      </thead>

                      <tbody>
                        {fines.map((fine) => (
                          <tr key={fine.id}>
                            <td>{fine.id}</td>
                            <td>{fine.fineNumber}</td>
                            <td>{fine.vehicleNumber}</td>
                            <td>
                              ₹{Number(fine.amount).toFixed(2)}
                            </td>

                            <td>
                              <span
                                className={`badge ${
                                  fine.paid
                                    ? 'bg-success'
                                    : 'bg-warning text-dark'
                                }`}
                              >
                                {fine.paid ? 'Paid' : 'Unpaid'}
                              </span>
                            </td>

                            <td>
                              <button
                                type="button"
                                className="btn btn-danger btn-sm"
                                onClick={() => deleteFine(fine.id)}
                              >
                                Delete
                              </button>
                            </td>
                          </tr>
                        ))}
                      </tbody>
                    </table>
                  </div>
                )}
              </div>
            </div>

          </div>
        </div>
      </main>
    </div>
  );
}

export default App;
