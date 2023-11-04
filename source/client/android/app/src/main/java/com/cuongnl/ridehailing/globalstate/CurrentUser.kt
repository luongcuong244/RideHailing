package com.cuongnl.ridehailing.globalstate

import com.cuongnl.ridehailing.enums.AddressType
import com.cuongnl.ridehailing.models.Address
import com.cuongnl.ridehailing.models.User

object CurrentUser {

    private var user: User? = null

    fun getUser(): User? {
        return user
    }

    fun setUser(user: User) {
        this.user = user
        this.user?.apply {
            addAddress(Address(AddressType.HOME, "123 Nguyen Van Linh", "777 Brockton Avenue, Abington MA 2351",10.762622, 106.660172))
            addAddress(Address(AddressType.WORK, "123 Nguyen Van Linh", "30 Memorial Drive, Avon MA 2322",10.762622, 106.660172))
            addAddress(Address(AddressType.OTHER, "123 Nguyen Van Linh", "337 Russell St, Hadley MA 1035",10.762622, 106.660172))
        }
    }
}