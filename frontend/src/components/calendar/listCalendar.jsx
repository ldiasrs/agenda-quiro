import {NavigationApp} from "../navigation-app";
import React, {useEffect, useState} from "react";
import {Link, useLocation} from 'react-router-dom';
import api from "../../services/api";
import {Pagination} from "../Pagination";
import {FaHammer} from "react-icons/fa";

export const ListCalendar = ({ history, match }) => {

    const search = useLocation().search;
    const searchTerm = new URLSearchParams(search).get('searchTerm');

    const paginationSize = 20

    const [tableFilter, setTableFiler] = useState(undefined);
    const [calendarData, setServiceTypeData] = useState(undefined);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(undefined);
    const [totalElements, setTotalElements] = useState(undefined);

    const handlePrevNavigation = async () => {
        updateCurrentPage(currentPage-1)
    }

    const updateCurrentPage =  (newPage) => {
        if (newPage >= 0 && newPage<totalPages) {
            setCurrentPage(newPage)
        }
    }

    const handleNextNavigation = async () => {
        updateCurrentPage(currentPage+1)
    }

    const fetchItems = async () => {
        try {
            console.log('searchTerm:' + searchTerm)
            const params = {
                searchTerm: searchTerm,
                page: currentPage,
                size: paginationSize
            };
            console.log(params)
            return await api.get('/calendar', {params})
        } catch (error) {
            console.error("Ocorreu um erro ao buscar os items" + error);
        }
    }

    useEffect(() => {
        async function fetchData() {
            const response = await fetchItems()
            setTotalPages(response.data.totalPages)
            setTotalElements(response.data.totalElements)
            setServiceTypeData(response.data.content)
        }
        fetchData();
    }, [currentPage]);

    const filterServiceType = (calendar) => {
        if (!tableFilter) return true;
        return ((calendar.description).includes(tableFilter))
    }

    const filterTableElements = () => {
        let key = 0
        if (!calendarData) return;
        return calendarData
            .filter(filterServiceType)
            .map(calendar =>
                <tr key={key++}>
                    <td>{calendar.description}</td>
                    <td>{calendar.durationInMinutes}</td>
                    <td>
                        <Link to={`/servico/${calendar.id}`} className="btn btn-sm btn-primary mr-1">Edit</Link>
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
                                <h2 className="list-tile"> <FaHammer/> Tipos de serviço</h2>
                            </div>

                            <div className="dropdown">
                                <button className="btn btn-secondary dropdown-toggle" type="button"
                                        id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                        aria-expanded="false">
                                    Dropdown button
                                </button>
                                <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                    <a className="dropdown-item" href="#">Action</a>
                                    <a className="dropdown-item" href="#">Another action</a>
                                    <a className="dropdown-item" href="#">Something else here</a>
                                </div>
                            </div>

                            <div className="col-sm-6">
                                <form className="form-inline">
                                    <div className="form-group">
                                        <input className="form-control" id="myInput" type="text" placeholder="Pesquisar..." onChange={e => setTableFiler(e.target.value)}/>
                                        <a href={`/listarservico?searchTerm=${tableFilter || ''}`} className="btn btn-search"><span className="span-search">Pesquisar...</span></a>
                                    </div>
                                </form>
                            </div>

                            <div className="col-sm-2">
                                <a href="/registrarservico" className="btn btn-add" data-toggle="modal">
                                    <span className="span-add">Adicionar</span></a>
                            </div>
                        </div>
                    </div>
                    <table className="table table-striped table-hover">
                        <thead>
                        <tr>
                            <th>Descrição</th>
                            <th>Duração (minutos)</th>
                        </tr>
                        </thead>
                        <tbody>
                        {
                            filterTableElements()
                        }
                        </tbody>
                    </table>
                    <Pagination
                        handlePrevNavigation={handlePrevNavigation}
                        handleNextNavigation={handleNextNavigation}
                        totalElements={totalElements}
                        totalPages={totalPages}
                        maxItemsPerPage={paginationSize}
                    />
                </div>
            </div>
        </>
    )
}
