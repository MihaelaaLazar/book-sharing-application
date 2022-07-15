import './App.css';
import Header from "./components/header/Header";
import {HorizontalLine} from "./components/header/Header.style";

function App({children}) {
  return (
      <div className="main">
          <Header/>
          <HorizontalLine/>
          {children}
      </div>
  );
}

export default App;
