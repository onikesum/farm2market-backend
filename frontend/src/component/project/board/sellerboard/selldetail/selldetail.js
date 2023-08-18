import React, { useEffect, useState } from 'react';
//import { useParams } from 'react-router-dom';
import {useParams, Link, useNavigate} from 'react-router-dom'; // Link 컴포넌트 추가
import { useSelector } from 'react-redux';
import {
    ChatButton, ChatButton2, ChatLogo, ContactBox, ContactContent, ContentBox, ContentImg,
    Explan, ExplanSpan, IndexContainer, InfoText, InfoTextSpan,
    IntroBox, IntroContent, IntroTitle, Mainimg, ProfileImg, SellerInfo,
    SellerInfoBox, Selltest, WantSellerBox, WantSellerContent
} from './component';
import axios from "axios";
import {Inners} from "../../../../emotion/component";


function SellDetail() {
    const [data, setData] = useState({});
    const token = useSelector(state => state.token);
    const [imageData, setImageData] = useState(null);
    const navigate = useNavigate();
    const { id } = useParams(); // 바깥으로 이동

    useEffect(() => {
        axios.get(`/seller-board/${id}`, {
            headers: {
                'X-AUTH-TOKEN': `${token}`
            }
        })
            .then(response => {
                const dataArray = response.data;
                setData(dataArray);
                console.log(dataArray.imageId); // 확인용
                return dataArray.imageId; // 이미지 ID 반환
            })
            .then(imageId => {
                // 이미지 데이터를 가져오는 API 호출
                axios.get(`/image/${imageId}`, {
                    headers: {
                        'X-AUTH-TOKEN': token
                    },
                    params: {
                        fileId: imageId,
                    },
                    responseType: 'arraybuffer'
                })
                    .then((response) => {
                        console.log('이미지 데이터 성공:', response.data);
                        setImageData(new Uint8Array(response.data));
                    })
                    .catch((error) => {
                        console.error('이미지 데이터 업로드 에러:', error);
                        // Handle error response
                    });
            })
            .catch(error => {
                console.error('Error fetching data:', error);
            });
    }, [id, token]);

    function handleDelete() {
        axios.delete(`/seller-board?id=${id}`, { // id 사용
            headers: {
                'X-AUTH-TOKEN': `${token}`
            }
        })
            .then(response => {
                console.log("성공적으로 삭제되었습니다.", response.data);
                navigate("/sellerboard");
            })
            .catch(error => {
                console.error('Error deleting data:', error);
            });
    }
    return (
        <Inners>
            {imageData && (
                <Mainimg
                    src={`data:image/png;base64,${btoa(String.fromCharCode(...imageData))}`}
                    alt="이미지"
                />
            )}
            <ContentBox>
                <SellerInfoBox>
                    <SellerInfo>
                        <ProfileImg />
                        <InfoText>
                            {data.name && (
                                <>
                                    {data.name}<br />
                                    <InfoTextSpan>
                                        공급자 회원
                                    </InfoTextSpan>
                                </>
                            )}
                        </InfoText>
                    </SellerInfo>
                    <Selltest>
                        <ChatButton>
                            <ChatLogo />
                            채팅으로 문의하기
                        </ChatButton>
                        <ChatButton2 onClick={handleDelete}>삭제</ChatButton2> {/* 삭제 버튼 */}
                        <Link to={`/sellerboard/selldetail/${id}/sellupdate`}>
                            <ChatButton2>수정하기</ChatButton2>
                        </Link>
                    </Selltest>

                </SellerInfoBox>
                <Explan>
                    영양가 높은{' '}
                    <ExplanSpan>{data.product}</ExplanSpan>
                    판매 중입니다.
                </Explan>
                <IntroBox>
                    <IntroTitle>소개 정보</IntroTitle>
                    <IntroContent>
                        {data.name && (
                            <>
                                믿을 수 있는 국내 산지에서 수확한 {data.product}를 키우고 있는 {data.name}입니다. 저희 {data.name}에서는 소비자가 믿고 먹을 수 있도록 항상 최상의 품질만을 보장하고 있습니다.
                                {data.product}의 가격은 {data.price}입니다.
                            </>
                        )}
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
            </ContentBox>
        </Inners>
    );
}

export default SellDetail;