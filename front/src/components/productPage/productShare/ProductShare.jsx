import React, {useState} from 'react'
import './productShare.css'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {  faShareAlt, faHeart } from '@fortawesome/free-solid-svg-icons'
import { FacebookShareButton ,FacebookIcon, TwitterShareButton, TwitterIcon, InstapaperShareButton, InstapaperIcon, WhatsappShareButton, WhatsappIcon, EmailIcon, EmailShareButton, LinkedinIcon, LinkedinShareButton } from 'react-share'

const ProductShare = () => {

    const [mostrar, setMostrar] = useState(false);
    let location = (window.location.href)

    const url = location
    

  return (
    <div className='shareContainer'>
        <FontAwesomeIcon className='iconsFuntional' icon={faHeart}/>
        <FontAwesomeIcon className='iconsFuntional' id='iconShare' onClick={() => setMostrar(!mostrar) } icon={faShareAlt}/>
        <div className={`shareIcons ${ mostrar? 'active' : 'inactive'} `} >
            <div >
                <FacebookShareButton className='itemShare'  url={url}>
                    <FacebookIcon size="30px" round= {true}/>
                </FacebookShareButton>

                <TwitterShareButton className='itemShare' url={url}>
                    <TwitterIcon size="30px" round= {true}/>
                </TwitterShareButton>

                <InstapaperShareButton className='itemShare' url={url}>
                    <InstapaperIcon size="30px" round= {true}/>
                </InstapaperShareButton>
            </div>
            <div >
                <WhatsappShareButton className='itemShare' url={url}>
                    <WhatsappIcon size="30px" round= {true}/>
                </WhatsappShareButton>

                <EmailShareButton className='itemShare' url={url}>
                    <EmailIcon size="30px" round= {true}/>
                </EmailShareButton>

                <LinkedinShareButton className='itemShare' url={url}>
                    <LinkedinIcon size="30px" round= {true}/>
                </LinkedinShareButton>
            </div>
        </div>
    </div>
  )
}

export default ProductShare