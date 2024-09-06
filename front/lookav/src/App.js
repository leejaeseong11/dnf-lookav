import './App.css';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Avatar from './pages/avatar/Avatar';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Routes>
          <Route path="/avatar" element={<Avatar />}></Route>
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
