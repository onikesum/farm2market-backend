import styled from "styled-components";
import searchLogo from "../../../img/header/searchLogo.svg";
import searchslideLogo from "../../../img/header/searchslideLogo.svg";

export const HeaderContainer = styled.header`
  position: relative;
  width: 100%;
  height: 66px;
  background-color: #76c56f;
  display: flex;
  align-items: center;
  margin-bottom: 50px;
  box-sizing:border-box;
`;

export const Logo = styled.div`
  margin-left: 147px;
  margin-top: 5px;
`;

export const Nav = styled.nav`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 910px;
  margin-left: 87px;
`;

export const NavItem = styled.a`
  color: #fff;
  width: 179px;
  font-feature-settings: "clig" off, "liga" off;
  font-family: Inter;
  font-size: 20px;
  font-style: normal;
  font-weight: 600;
  line-height: 20px;
  text-decoration: none;
`;

export const SearchBar = styled.input.attrs({
    type: "text",
})`
  width: 333px;
  height: 33px;
  flex-shrink: 0;
  border-radius: 4px;
  border: none;
  margin-left: 80px;
  padding-left: 30px;
  padding-right: 30px;
  background-image: url(${searchLogo}), url(${searchslideLogo});
  background-position: 10px center, calc(100% - 10px) center;
  background-repeat: no-repeat, no-repeat;
  box-sizing: border-box;
`;

export const LoginButton = styled.div`
  width: 70px;
  height: 33px;
  flex-shrink: 0;
  border-radius: 55px;
  border: 1px solid #fff;
  color: white;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  font-size: 14px;
  margin-left: 55px;
`;

export const DropdownMenu = styled.div`
  position: absolute;
  top: 66px;
  width: 1920px;
  left: 0;
  height: 210px;
  background-color: #76c56f;
  z-index: 10;
`;

export const DropNav = styled.nav`
  display: flex;
  justify-content: space-between;
  width: 914px;
  background-color: #76c56f;
  margin-left: 254px;
`;

export const DropNavGroup = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 37px;
  width: 179px;
  background-color: #76c56f;
`;

export const DropItem = styled.a`
  color: white;
  text-decoration: none;
  height: 31px;
  margin: 10px 0;
`;
