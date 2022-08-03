import React, {Fragment} from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import GlobalCSS from './globalCss.style';
import Register from "./components/register/Register";
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import Home from "./components/home/Home";
import Login from "./components/login/Login";
import {Provider} from "react-redux";
import store from "./reducers/store";
import UserPage from "./components/user-page/UserPage";
import CreateBook from "./components/user-page/books/CreateBook";
import RequireAuth from "./guard/RequireAuth";
import SearchResults from "./components/search/SearchResults";
import NotFound from "./views/not-found/NotFound";

const root = ReactDOM.createRoot(document.getElementById('root'));


root.render(<Fragment>
    <GlobalCSS/>
    <Provider store={store}>
        <Router>
            <App>
                <Routes>
                    <Route path="/" exact={true} element={<Home/>}/>
                    <Route path="/register" exact={true} element={<Register/>}/>
                    <Route path="/login" exact={true} element={<Login/>}/>
                    <Route path="/dashboard" exact={true} element={<RequireAuth> <UserPage/> </RequireAuth>}/>
                    <Route path="/dashboard/create" exact={true}
                           element={<RequireAuth> <CreateBook/> </RequireAuth>}/>
                    <Route path="/search/:query" exact={true}
                           element={<RequireAuth><SearchResults/></RequireAuth>}/>
                    <Route path="*" element={<NotFound/>}/>
                </Routes>
            </App>
        </Router>
    </Provider>
</Fragment>);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
