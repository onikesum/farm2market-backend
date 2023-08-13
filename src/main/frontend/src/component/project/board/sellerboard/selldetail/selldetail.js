import React from 'react';
import { ChatButton, ChatLogo, ContactBox, ContactContent, ContentBox, ContentImg,
     Explan, ExplanSpan, IndexContainer, InfoText, InfoTextSpan, 
     IntroBox, IntroContent, IntroTitle, ProfileImg, SellerInfo, 
     SellerInfoBox, WantSellerBox, WantSellerContent } from './component';


function SellDetail() {
    return (
        <IndexContainer>
            <ContentBox>
                <ContentImg/>
                <SellerInfoBox>
                    <SellerInfo>
                        <ProfileImg/>
                        <InfoText>
                            뽀빠이 농장
                            <br/>
                            <InfoTextSpan>
                            공급자 회원
                            </InfoTextSpan>
                        </InfoText>
                    </SellerInfo>
                    <ChatButton>
                            <ChatLogo/>
                            채팅으로 문의하기
                    </ChatButton>
                </SellerInfoBox>
                <Explan>
                먹을 때마다 힘이 불끈! 영양가 높은 
                <ExplanSpan>시금치</ExplanSpan>
                판매 중입니다.
                </Explan>
                <IntroBox>
                    <IntroTitle>소개 정보</IntroTitle>
                    <IntroContent>
                    믿을 수 있는 국내 산지에서 수확한 시금치를 키우고 있는 뽀빠이 농장입니다. 저희 뽀빠이 농장에서는 소비자가 믿고 먹을 수 있도록 항상 최상의 품질만을 보장하고 있습니다. 
                    직접 농사하여 싱싱하고 영양가가 매우 높고 맛 좋은 상품입니다. 
                    </IntroContent>
                </IntroBox>
                <WantSellerBox>
                    <IntroTitle>원하는 판매자</IntroTitle>
                    <WantSellerContent>
                        <ul>
                            <li>다양한 플랫폼을 사용하는 판매자</li>
                            <li>성실한 판매자</li>
                            <li>경험이 많은 판매자</li>
                        </ul>
                    </WantSellerContent>
                </WantSellerBox>
                <ContactBox>
                    <IntroTitle>연락처</IntroTitle>
                    <ContactContent>
                        031-000-0000
                    </ContactContent>
                </ContactBox>
            </ContentBox>
        </IndexContainer>
        );
    }
    
    export default SellDetail;
    