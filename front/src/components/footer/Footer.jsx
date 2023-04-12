import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faFacebook, faLinkedinIn, faTwitter, faInstagram} from '@fortawesome/free-brands-svg-icons';
import styles from './Footer.module.css';

const Footer = () => {
    return (
    <div className={styles.footer + " container"}>
        <div className={styles.elements}>
            <div className={styles.copyright}>
                <p>©2023 Digital Booking</p>
            </div>
            <div className={styles.icons}>
                <FontAwesomeIcon icon={faFacebook} />
                <FontAwesomeIcon icon={ faLinkedinIn} />
                <FontAwesomeIcon icon= {faTwitter}/>
                <FontAwesomeIcon icon= {faInstagram}/>
            </div>
        </div>
    </div>
)}
export default Footer