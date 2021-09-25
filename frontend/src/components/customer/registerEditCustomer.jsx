import {NavigationApp} from "../navigation-app";

export const RegisterEditCustomer = (props) => {

    return (
        <>
            <NavigationApp/>
            <div className="container" className='customForm'>
                <div id="addCustomerModal" >
                    <div>
                        <div>
                            <form>
                                <div className="modal-header">
                                    <h4 className="modal-title">Adicionar</h4>
                                    <button type="button" className="close" data-dismiss="modal"
                                            aria-hidden="true">&times;</button>
                                </div>
                                <div className="modal-body">
                                    <div className="form-group">
                                        <label>Name</label>
                                        <input type="text" className="form-control" required/>
                                    </div>
                                    <div className="form-group">
                                        <label>Email</label>
                                        <input type="email" className="form-control" required/>
                                    </div>
                                    <div className="form-group">
                                        <label>Address</label>
                                        <textarea className="form-control" required></textarea>
                                    </div>
                                    <div className="form-group">
                                        <label>Phone</label>
                                        <input type="text" className="form-control" required/>
                                    </div>
                                </div>
                                <div className="modal-footer">
                                    <input type="button" className="btn btn-default" data-dismiss="modal" value="Cancel"/>
                                    <input type="submit" className="btn btn-success" value="Add"/>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </>
    )
}
