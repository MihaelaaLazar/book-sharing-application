import React, {Fragment} from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
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
