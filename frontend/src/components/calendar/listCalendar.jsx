import {NavigationApp} from "../navigation-app";
import React, {useEffect, useState} from "react";
import {Link, useLocation} from 'react-router-dom';
import api from "../../services/api";
import {FaHammer} from "react-icons/fa";
import {getLoginUseId} from "../../services/auth";
import {isFreeStatus, statusNames} from "./statusNames";
import {parseAndFormatDate, parseAndFormatHour} from "../utils/utilis";
import {ConfirmationModal} from "../ConfirmationModal";


export const ListCalendar = ({history, match}) => {


    const search = useLocation().search;
    const searchTerm = new URLSearchParams(search).get('searchTerm');

    const [tableFilter, setTableFiler] = useState(undefined);
    const [calendarData, setCalendarData] = useState(undefined);

    const fetchItems = async () => {
        try {
            console.log('searchTerm:' + searchTerm)
            const currentDate = new Date();
            const params = {
                startPeriod: undefined,
                endPeriod: undefined
            };
            console.log("params: " + params)
            const userId = getLoginUseId();
            return await api.get(`/calendar/user/${userId}`, {params})
        } catch (error) {
            console.error("Ocorreu um erro ao buscar os items" + error);
        }
    }

    useEffect(() => {
        async function fetchData() {
            const response = await fetchItems()
            console.log("response: " + JSON.stringify(response.data))
            setCalendarData(response.data)
        }

        fetchData();
    }, [2]);

    const filterCalendar = (periodSlot) => {
        if (!tableFilter) return true;
        return ((periodSlot.description
            + periodSlot.clientName
            + periodSlot.clientPhone
            + periodSlot.observation
            + periodSlot.serviceName
            + statusNames[periodSlot.status]
            + parseAndFormatDate(periodSlot.date)
            + parseAndFormatHour(periodSlot.startTime)
        ).includes(tableFilter))
    }

    const getActionLink = (status, appointmentId) => {
        const isFreeSlot = isFreeStatus(status)
        const destination = isFreeSlot ? '/agendar' : '#deleteServiceTypeModal-'+appointmentId
        const style = isFreeSlot ? "action-btn-green" : "action-btn-red"
        const actionLink = <a href={destination} data-toggle="modal">
            <button className={"btn btn-sm btn-primary " + style} >{isFreeSlot ? "Agendar" : "Cancelar"}</button>
        </a>
        if (isFreeSlot) {
            return actionLink
        }
        return <div>
            {actionLink}
            <ConfirmationModal
                history={history}
                deleteApiPath='/appointments'
                listRoutePath='/agenda'
                id={appointmentId}
            />
        </div>
    }

    const filterTableElements = () => {
        let key = 0
        if (!calendarData) return;
        return calendarData.periodSlots
            .filter(filterCalendar)
            .map(slot =>
                <tr key={key++}>
                    <td>{parseAndFormatDate(slot.date)}</td>
                    <td>{parseAndFormatHour(slot.startTime)} - {parseAndFormatHour(slot.endTime)}</td>
                    <td>{statusNames[slot.status]}</td>
                    <td>{slot.serviceName}</td>
                    <td>{slot.clientName} ({slot.clientPhone})</td>
                    <td>R$ {slot.amountPaid}</td>
                    <td>{slot.observation}</td>
                    <td>
                        {getActionLink(slot.status, slot.appointmentId)}
                    </td>
                </tr>
            )
    }

    return (
        <>
            <NavigationApp/>
            <div className='customForm'>
                <div className="table-wrapper">
                    <div className="table-title">
                        <div className="row-title">
                            <div className="col-sm-4">
                                <h2 className="list-tile"><FaHammer/> Agenda</h2>
                            </div>

                            <div className="col-sm-6">
                                <form className="form-inline">
                                    <div className="form-group">
                                        <input className="form-control" id="myInput" type="text"
                                               placeholder="Pesquisar..."
                                               onChange={e => setTableFiler(e.target.value)}/>
                                        <a href={`/agenda?searchTerm=${tableFilter || ''}`}
                                           className="btn btn-search"><span className="span-search">Pesquisar...</span></a>
                                    </div>
                                </form>
                            </div>

                            <div className="col-sm-2">
                                <a href="/registrarservico" className="btn btn-add" data-toggle="modal">
                                    <span className="span-add">Adicionar</span></a>
                            </div>
                        </div>
                    </div>
                    <table className="table table-striped table-hover content-table-adjust">
                        <thead>
                        <tr>
                            <th>Data</th>
                            <th>Horário</th>
                            <th>Status</th>
                            <th>Serviço</th>
                            <th>Cliente</th>
                            <th>Pago</th>
                            <th>Observação</th>
                            <th>Ações</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            filterTableElements()
                        }
                        </tbody>
                    </table>
                </div>
            </div>
        </>
    )
}
