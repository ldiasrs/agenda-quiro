export const Pagination = (props) => {
    const maxItemsPerPage = 10;

    const fetchCustomers = () => {
        let customers = []
        for (let i = 0; i < 10; i++) {
            customers.push(
                {"id":1,"name":"leo"+i,"email":i+"leo@gmail.com","phone":"9039409343","cpf":"0092323","birthDate":{"year":2021,"month":"SEPTEMBER","chronology":{"calendarType":"iso8601","id":"ISO"},"era":"CE","monthValue":9,"dayOfMonth":23,"dayOfYear":266,"dayOfWeek":"THURSDAY","leapYear":false},"height":"92","weight":"178","gender":"masculino"}
            )
        }
        return {
            totalItems: 50,
            customers: customers,
            totalPages: 5,
            currentPage: 1
        };
    }

    const getTotalItems = () => {
        return maxItemsPerPage
    }


    const paginationData = fetchCustomers()

    // const { currentPage } = this.state;
    // const pages = this.fetchPageNumbers();

    const getPagesClick = () => {
        const pages = []
        for (let i = 0; i < paginationData.totalPages; i++) {
            pages.push(<li className="page-item"><a href="#" className="page-link">{i+1}</a></li>)
        }
        return pages
    }

    return (
        <div className="clearfix">
            <div className="hint-text">Showing <b>{getTotalItems()}</b> out of <b>{paginationData.totalItems}</b> entries</div>
            <ul className="pagination">
                <li className="page-item disabled"><a href="#">Previous</a></li>
                {
                    getPagesClick()
                }
                <li className="page-item"><a href="#" className="page-link">Next</a></li>
            </ul>
        </div>
    );
}
