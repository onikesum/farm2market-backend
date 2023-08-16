import React, { useState,useEffect } from "react";
import menuLogo from "../../../img/header/menuLogo.svg";
import { Link, useNavigate } from "react-router-dom";
import {
    HeaderContainer,
    Logo,
    Nav,
    NavItem,
    DropdownMenu,
    DropNav,
    DropNavGroup,
    DropItem,
    SearchBar,
    LoginButton,
} from "./component";
import mast from "../../../img/main/Frame.svg"
const Header = () => {
    const [isBottomNavActive, setBottomNavActive] = useState(false);
    const navigate = useNavigate();

    const handleLogoClick = () => {
        setBottomNavActive(!isBottomNavActive);
    };

    useEffect(() => {
        // 페이지 전환 시 자동으로 isBottomNavActive를 false로 설정
        setBottomNavActive(false);
    }, [navigate]);

    const handleNavItemClick = (path) => {
        setBottomNavActive(false);
        navigate(path); // 해당 path로 페이지 이동
    };

    return (
        <HeaderContainer>
            <Logo>
                <Link to={"/"}>
                    <img src={mast} width="50px" height="50px" alt="Mast" />
                </Link>
                <div onClick={handleLogoClick} style={{ cursor: "pointer" }}>
                    <img src={menuLogo} alt="menuLogo" />
                </div>
            </Logo>
            <Nav>
                <NavItem as={Link} to="/proboard">
                    게시판
                </NavItem>
                <NavItem as={Link} to="/agriboard">
                    농산물
                </NavItem>
                <NavItem as={Link} to="/community">
                    커뮤니티
                </NavItem>
                <NavItem as={Link} to="/mypage">
                    마이페이지
                </NavItem>
            </Nav>
            {isBottomNavActive && (
                <DropdownMenu>
                    <DropNav>
                        <DropNavGroup>
                            <DropItem as={Link} to="/proboard">
                                판매자 정보 게시판
                            </DropItem>
                            <DropItem as={Link} to="/sellerboard">
                                농산물 등록 게시판
                            </DropItem>
                            <DropItem as={Link} to="/Inquiry/personal">
                                고객 문의
                            </DropItem>
                        </DropNavGroup>
                        <DropNavGroup>
                            <DropItem as={Link} to="/agriboard">
                                가격 정보 보기
                            </DropItem>
                        </DropNavGroup>
                        <DropNavGroup>
                            <DropItem as={Link} to="/community">
                                소통 하기
                            </DropItem>
                        </DropNavGroup>
                        <DropNavGroup>
                            <DropItem as={Link} to="/info">
                                정보 수정
                            </DropItem>
                        </DropNavGroup>
                    </DropNav>
                </DropdownMenu>
            )}
            <SearchBar placeholder="검색어를 입력하세요" />
            <Link to={"/signin" }>
                <LoginButton>로그인</LoginButton>
            </Link>
        </HeaderContainer>
    );
};

export default Header;
