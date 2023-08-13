import React, { useEffect } from 'react';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Main from "./component/project/main/main";
import Mypage from "./component/project/mypage/mypage";
import Signin from "./component/project/signin/signin";
import Signup from "./component/project/signup/signup";
import Proboard from "./component/project/board/proboard";
import Sellerboard from "./component/project/board/sellerboard";
import Agriboard from "./component/project/board/agricultboard";
import Community from "./component/project/community";
import PersonalInquiry from "./component/project/Inquiry/personInquiry";
import Findid from "./component/project/find/findid";
import Findpassword from "./component/project/find/findpassword";
import Findresultid from "./component/project/find/findid/result";
import Findresultpass from "./component/project/find/findpassword/result";
import Aiwriting from "./component/project/writing/aiwriting/aiwriting";
import "./App.css";
import Header from "./component/project/header/header";
import { Provider,useDispatch } from 'react-redux';
import store from './redux/store';

function App() {
    return (
        <div>
            <Provider store={store}>
                <BrowserRouter>
                    <Header/>
                    <Routes>
                        <Route path="/" element={<Main />} />
                        <Route path="/mypage" element={<Mypage />} />
                        <Route path="/signin" element={<Signin />} />
                        <Route path="/signup" element={<Signup />} />
                        <Route path="/proboard" element={<Proboard />} />
                        <Route path="/sellerboard" element={<Sellerboard />} />
                        <Route path="/agriboard" element={<Agriboard />} />
                        <Route path="/community" element={<Community />} />
                        <Route path="/Inquiry/personal" element={<PersonalInquiry />} />
                        <Route path="/findid" element={<Findid />} />
                        <Route path="/findpass" element={<Findpassword />} />
                        <Route path="/findid/result" element={<Findresultid />} />
                        <Route path="/findpass/result" element={<Findresultpass />} />
                        <Route path="/aiwriting" element={<Aiwriting />} />
                    </Routes>
                </BrowserRouter>
            </Provider>
        </div>
    );
}

export default App;
