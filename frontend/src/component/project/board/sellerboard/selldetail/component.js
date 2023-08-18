import styled from 'styled-components';
const emptyImageURL = 'data:image/png;base64,iVBORw...'; // 빈 이미지의 Base64 데이터




export const ContentBox = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  flex-direction: column;
  align-items: center;

`;

export const ContentImg = styled.div`
  background: url(${emptyImageURL}) no-repeat center center;
  width: 1132px;
  height: 646px;
  flex-shrink: 0;
  border-radius: 10px;
`;

export const SellerInfoBox = styled.div`
  width: 100%;
  height: auto;
  display: flex;
  align-items: center;
  justify-items: center;
  text-align: center;
  gap: 10rem;
  
`;
export const Selltest = styled.div`
  height: auto;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-items: center;
  text-align: center;
  gap: 0.3rem;
  
`;
export const SellerInfo = styled.div`
  width: 50%;
  display: flex;
  justify-content: space-between;
`;

export const ProfileImg = styled.div`
  background: url(${emptyImageURL}) no-repeat center center;
  width: 97px;
  height: 97px;
  flex-shrink: 0;
`;
export const InfoText = styled.h1`
  color: #000;
  font-size: 32px;
  font-style: normal;
  font-weight: 600;
  line-height: 35px;
`;
export const Mainimg = styled.img`
    width: 100%;
  height: 20rem;
`;
export const InfoTextSpan = styled.span`
  color: #000;
  font-size: 20px;
  font-style: normal;
  font-weight: 400;
  line-height: 20px; /* 100% */
`;

export const ChatButton = styled.div`
  width: 18rem;
  height: 4rem;
  border-radius: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  color: #FFF;
  background: #76C56F;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 20px; /* 83.333% */
  cursor: pointer;
`;
export const ChatButton2 = styled.div`
  width: 10rem;
  height: 2rem;
  border-radius: 0.5rem;
  background: #76C56F;
  display: flex;
  justify-content: center;
  align-items: center;
  text-align: center;
  color: #FFF;
  font-size: 24px;
  font-style: normal;
  font-weight: 600;
  line-height: 20px; /* 83.333% */
  cursor: pointer;
`;

export const ChatLogo = styled.div`
  background: url(${emptyImageURL}) no-repeat;
  width: 41px;
  height: 40px;
  flex-shrink: 0;
  margin-right: 5px;
`;

export const Explan = styled.div`
  width: 1092px;
  height: 123px;
  flex-shrink: 0;
  padding-left: 40px;
  border-radius: 5px;
  background: #F8F9E3;
  display: flex;
  align-items: center;
  color: #3A6E67;
  font-size: 28px;
  font-style: normal;
  font-weight: 400;
  margin-top: 50px;
`;

export const ExplanSpan = styled.span`
  color: #3A6E67;
  font-size: 28px;
  font-style: normal;
  font-weight: 600;
  line-height: 20px;
`;

export const IntroBox = styled.div`
  width: 1132px;
  flex-shrink: 0;
  margin-top: 50px;
  display: flex;
  flex-direction: column;
`;

export const IntroTitle = styled.div`
  color: #000;
  font-feature-settings: 'clig' off, 'liga' off;
  font-family: Inter;
  font-size: 32px;
  font-style: normal;
  font-weight: 600;
`;

export const IntroContent = styled.div`
  color: #3A6E67;
  font-feature-settings: 'clig' off, 'liga' off;
  font-family: Inter;
  font-size: 24px;
  font-style: normal;
  font-weight: 400;
  margin-top: 25px;
`;

export const WantSellerBox = styled.div`
  width: 1132px;
  display: flex;
  felx-direction: column;
  margin-top: 50px;
`;

export const WantSellerContent = styled.div`
  color: #3A6E67;
  font-feature-settings: 'clig' off, 'liga' off;
  font-family: Inter;
  font-size: 24px;
  font-style: normal;
  font-weight: 400;
  display: flex;
  align-items: flex-start;
  margin-top: 45px;
  margin-left: -200px;
`;

export const ContactBox = styled.div`
  width: 1132px;
  display: flex;
  flex-direction: column;
  margin-top: 50px;
`;

export const ContactContent = styled.div`
  color: #3A6E67;
  font-feature-settings: 'clig' off, 'liga' off;
  font-family: Inter;
  font-size: 24px;
  font-style: normal;
  font-weight: 400;
  line-height: 36px; /* 150% */
  margin-top: 25px;
`;