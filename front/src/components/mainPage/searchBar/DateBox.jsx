import React, { useEffect, useState, forwardRef } from 'react';
import DatePicker, { registerLocale} from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import './datebox.css';
import es from 'date-fns/locale/es';
import { convert } from "../../../utils/utils.js"
import { useContextProvider } from '../../../context/UserContext'

const DateBox = (props) => {
    // const [startDate, setStartDate] = useState(null);
    // const [endDate, setEndDate] = useState(null);
    const setDateState = useContextProvider().setDateState
    const dateState = useContextProvider().dateState
    registerLocale('es', es);
    const onChange = (dates) => {
        const [start, end] = dates;
        setDateState({
            start: start,
            end: end,
            productId: props.productId
        })
        props.setStartDate((start));
        props.setEndDate(end);
    };

    const array = props.reservations ? props.reservations.map(reserva => {
        return {
            start: new Date(reserva.fechaInicial),
            end: new Date(reserva.fechaFinal)
        }
    }): []

    useEffect(()=> {
        if (props.productId == null) return;
        if (dateState.productId == null){
            setDateState(
                {
                    ...dateState, productId: props.productId
                })
        }
        else if (dateState.productId != props.productId){
            setDateState({
                start: null,
                end: null,
                productId: props.productId
            })
        }
    }, [])
    

    const CalendarInput = forwardRef(({ value, onClick, onChange }, ref) => (
        <input
            value={value}
            className="calendar-input"
            onClick={onClick}
            onChange={onChange}
            placeholder="Check in - Check Out"
            ref={ref}
        ></input>
        ));
    return (
        <div className='datepicker-container'>
            <DatePicker
            showIcon
            startDate={dateState.start}
            endDate={dateState.end}
            selected={dateState.start}
            onChange={onChange}
            customInput={<CalendarInput />}
            minDate={new Date()}
            dateFormat="yyyy/MM/dd"
            excludeDateIntervals={array}
            selectsRange
            monthsShown={2}
            locale="es"
            inline={props.inline}
            />
        </div>
    )
}

export default DateBox